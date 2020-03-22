package ru.relex.services.service;
import ru.relex.services.dto.organizer.ConferenceOrganizerDto;


import java.util.List;
public interface IConferenceOrganizerService {

        ConferenceOrganizerDto findUserIdByConference(int id);

        ConferenceOrganizerDto findConferenceByUserId(int id);

        void remove(int organizerId, int conferenceId);

}
