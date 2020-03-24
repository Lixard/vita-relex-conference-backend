package ru.relex.services.service;

import ru.relex.services.dto.organizer.ConferenceOrganizerDto;

import javax.validation.Valid;
import java.util.List;
public interface IConferenceOrganizerService {

        List<ConferenceOrganizerDto> getOrganizersByConferenceId(int id);

        List<ConferenceOrganizerDto> getConferencesByOrganizerId(int id);

        void assignToConference(@Valid ConferenceOrganizerDto conferenceOrganizerDto);

        void remove(int organizerId, int conferenceId);

        void resurrect(int organizerId, int conferenceId);

}
