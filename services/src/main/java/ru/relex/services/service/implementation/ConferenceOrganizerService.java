package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.db.mapper.ConferenceOrganizerMapper;
import ru.relex.services.dto.organizer.ConferenceOrganizerDto;
import ru.relex.services.mapstruct.ConferenceOrganizerStruct;
import ru.relex.services.service.IConferenceOrganizerService;

import java.util.List;

@Service
public class ConferenceOrganizerService implements IConferenceOrganizerService {
    private ConferenceOrganizerMapper conferenceOrganizerMapper;
    private ConferenceOrganizerStruct conferenceOrganizerStruct;


    @Autowired
    public ConferenceOrganizerService(ConferenceOrganizerMapper conferenceOrganizerMapper,ConferenceOrganizerStruct conferenceOrganizerStruct) {
        this.conferenceOrganizerMapper = conferenceOrganizerMapper;
        this.conferenceOrganizerStruct = conferenceOrganizerStruct;
    }

    @Override
    public List<ConferenceOrganizerDto> getOrganizersByConferenceId(int id) {
        return conferenceOrganizerStruct.toDto(conferenceOrganizerMapper.getOrganizersByConferenceId(id));
    }

    @Override
    public List<ConferenceOrganizerDto> getConferencesByOrganizerId(int id) {
        return conferenceOrganizerStruct.toDto(conferenceOrganizerMapper.getConferencesByUserId(id));
    }

    @Override
    public void remove(int organizerId, int conferenceId) {
        conferenceOrganizerMapper.delete(organizerId,conferenceId);
    }

    @Override
    public void resurrect(int organizerId, int conferenceId) {
        conferenceOrganizerMapper.resurrect(organizerId,conferenceId);
    }

}
