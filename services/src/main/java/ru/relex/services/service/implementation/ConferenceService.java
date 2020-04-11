package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.relex.commons.model.CurrentUser;
import ru.relex.db.mapper.ConferenceMapper;
import ru.relex.db.model.Conference;
import ru.relex.db.model.User;
import ru.relex.services.dto.conference.ConferenceDto;
import ru.relex.services.dto.user.UserAnswerDto;
import ru.relex.services.mapstruct.ConferenceStruct;
import ru.relex.services.mapstruct.UserAnswerStruct;
import ru.relex.services.service.IConferenceService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class ConferenceService implements IConferenceService {
    private ConferenceMapper conferenceMapper;
    private ConferenceStruct conferenceStruct;
    private final UserAnswerStruct userAnswerStruct;

    private final CurrentUser currentUser;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public ConferenceService(ConferenceMapper conferenceMapper,
                             ConferenceStruct conferenceStruct,
                             UserAnswerStruct userAnswerStruct,
                             CurrentUser currentUser) {
        this.conferenceMapper = conferenceMapper;
        this.conferenceStruct = conferenceStruct;
        this.userAnswerStruct = userAnswerStruct;
        this.currentUser = currentUser;
    }

    @Override
    public List<ConferenceDto> getConferences() {
        return conferenceStruct.toDto(conferenceMapper.getConferences());
    }

    @Override
    public UserAnswerDto getConferenceOwner(int conferenceId) {
        User user = conferenceMapper.getConferenceOwner(conferenceId);
        return userAnswerStruct.toAnswerDto(user);
    }

    @Override
    public List<ConferenceDto> getConferencesWhereUserIsOwner(int userId) {
        List<Conference> conferences = conferenceMapper.getConferencesWhereUserIsOwner(userId);
        List<ConferenceDto> conferenceDtos = new ArrayList<>();
        conferences.forEach(conference -> conferenceDtos.add(conferenceStruct.toDto(conference)));
        return conferenceDtos;
    }

    @Override
    public ConferenceDto findById(int conferenceId) {
        return conferenceStruct.toDto(conferenceMapper.findById(conferenceId));
    }

    @Override
    public ConferenceDto create(@Valid ConferenceDto conferenceDto) {
        Conference conference = conferenceStruct.fromDto(conferenceDto);
        conference.setOwner(currentUser.getId());
        conferenceMapper.insert(conference);
        return conferenceStruct.toDto(conference);
    }

    @Override
    public ConferenceDto update(@Valid ConferenceDto conferenceDto) {
        Conference conference = conferenceStruct.fromDto(conferenceDto);
        conferenceMapper.update(conference);
        Conference updatedConference = conferenceMapper.findById(conference.getConferenceId());
        return conferenceStruct.toDto(updatedConference);
    }

    @Override
    public void remove(int conferenceId) {
        conferenceMapper.delete(conferenceId);
    }

    @Override
    public void resurrect(int conferenceId) {
        conferenceMapper.resurrect(conferenceId);
    }
}
