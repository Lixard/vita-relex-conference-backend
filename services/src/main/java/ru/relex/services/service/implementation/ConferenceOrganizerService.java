package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import ru.relex.commons.model.CurrentUser;
import ru.relex.db.mapper.ConferenceMapper;
import ru.relex.db.mapper.ConferenceOrganizerMapper;
import ru.relex.db.mapper.UserMapper;
import ru.relex.db.model.ConferenceOrganizer;
import ru.relex.services.dto.conference.ConferenceDto;
import ru.relex.services.dto.organizer.ConferenceOrganizerDto;
import ru.relex.services.dto.user.UserDto;
import ru.relex.services.mapstruct.ConferenceOrganizerStruct;
import ru.relex.services.mapstruct.ConferenceStruct;
import ru.relex.services.mapstruct.UserStruct;
import ru.relex.services.service.IConferenceOrganizerService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class ConferenceOrganizerService implements IConferenceOrganizerService {
    private final ConferenceOrganizerMapper conferenceOrganizerMapper;
    private final ConferenceOrganizerStruct conferenceOrganizerStruct;
    private final ConferenceMapper conferenceMapper;
    private final ConferenceStruct conferenceStruct;
    private final UserStruct userStruct;
    private final UserMapper userMapper;
    private final CurrentUser currentUser;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public ConferenceOrganizerService(ConferenceOrganizerMapper conferenceOrganizerMapper,
                                      ConferenceOrganizerStruct conferenceOrganizerStruct,
                                      ConferenceMapper conferenceMapper,
                                      ConferenceStruct conferenceStruct,
                                      UserStruct userStruct,
                                      UserMapper userMapper,
                                      CurrentUser currentUser) {
        this.conferenceOrganizerMapper = conferenceOrganizerMapper;
        this.conferenceOrganizerStruct = conferenceOrganizerStruct;
        this.conferenceMapper = conferenceMapper;
        this.conferenceStruct = conferenceStruct;
        this.userMapper = userMapper;
        this.userStruct = userStruct;
        this.currentUser = currentUser;
    }

    @Override
    public List<UserDto> getOrganizersByConferenceId(int id) {
        List<ConferenceOrganizerDto> conferenceOrganizers = conferenceOrganizerStruct.toDto(conferenceOrganizerMapper.getOrganizersByConferenceId(id));
        List<UserDto> users = new ArrayList<>();
        conferenceOrganizers.forEach(organizers -> users.add(userStruct.toDto(userMapper.findById(organizers.getUserId()))));
        return users;
    }

    @Override
    public List<ConferenceDto> getConferencesByOrganizerId(int id) {
        List<ConferenceOrganizerDto> conferencesOfOrganizer = conferenceOrganizerStruct.toDto(conferenceOrganizerMapper.getConferencesByOrganizerId(id));
        List<ConferenceDto> conferencesWithDetails = new ArrayList<>();
        conferencesOfOrganizer.forEach(conferences -> conferencesWithDetails.add(conferenceStruct.toDto(conferenceMapper.findById(conferences.getConferenceId()))));
        return conferencesWithDetails;
    }

    @Override
    public void assignToConference(@Valid ConferenceOrganizerDto conferenceOrganizerDto) {
        ConferenceOrganizer conferenceOrganizer = conferenceOrganizerStruct.fromDto(conferenceOrganizerDto);
        conferenceOrganizer.setCreatedBy(currentUser.getId());
        if (conferenceOrganizerMapper.findDeletedById(conferenceOrganizer.getUserId(), conferenceOrganizer.getConferenceId()) != null) {
            conferenceOrganizerMapper.resurrect(conferenceOrganizer.getUserId(), conferenceOrganizer.getConferenceId());
            conferenceOrganizerMapper.update(conferenceOrganizer);
        } else if (conferenceOrganizerMapper.isExists(conferenceOrganizer.getUserId(), conferenceOrganizer.getConferenceId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RIGHTS_EXIST");
        } else {
            conferenceOrganizerMapper.insert(conferenceOrganizer);
        }
    }

    @Override
    public void remove(int conferenceId, int organizerId) {
        conferenceOrganizerMapper.delete(organizerId,conferenceId);
    }

    @Override
    public void resurrect(int conferenceId,int organizerId) {
        conferenceOrganizerMapper.resurrect(organizerId,conferenceId);
    }

}
