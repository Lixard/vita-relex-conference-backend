package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.db.mapper.ConferenceOrganizerMapper;
import ru.relex.services.dto.organizer.ConferenceOrganizerDto;
import ru.relex.services.mapstruct.ConferenceOrganizerStruct;
import ru.relex.services.service.IConferenceOrganizerService;

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
    public ConferenceOrganizerDto findUserIdByConference(int id) {
        ConferenceOrganizerDto user = conferenceOrganizerStruct.toDto(conferenceOrganizerMapper.findByConferenceId(id));
        return user;
    }

    @Override
    public ConferenceOrganizerDto findConferenceByUserId(int id) {
        ConferenceOrganizerDto conference = conferenceOrganizerStruct.toDto(conferenceOrganizerMapper.findByUserId(id));
        return conference;
    }

    @Override
    public void remove(int organizerId, int conferenceId) {
        conferenceOrganizerMapper.delete(organizerId,conferenceId);
    }

}
