package ru.relex.services.service;
import ru.relex.services.dto.organizer.ConferenceOrganizerDto;


import java.util.List;
public interface IConferenceOrganizerService {

        List<ConferenceOrganizerDto> getOrganizersByConferenceId(int id);

        List<ConferenceOrganizerDto> getConferencesByOrganizerId(int id);

        void remove(int organizerId, int conferenceId);

}
