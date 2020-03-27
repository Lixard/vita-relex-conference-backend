package ru.relex.services.service;

import ru.relex.services.dto.conference.ConferenceDto;
import ru.relex.services.dto.organizer.ConferenceOrganizerDto;
import ru.relex.services.dto.user.UserDto;

import javax.validation.Valid;
import java.util.List;
public interface IConferenceOrganizerService {

        List<UserDto> getOrganizersByConferenceId(int id);

        List<ConferenceDto> getConferencesByOrganizerId(int id);

        void assignToConference(@Valid ConferenceOrganizerDto conferenceOrganizerDto);

        void remove(int organizerId, int conferenceId);

        void resurrect(int organizerId, int conferenceId);

}
