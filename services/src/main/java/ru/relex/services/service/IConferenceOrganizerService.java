package ru.relex.services.service;

import ru.relex.services.dto.organizer.ConferenceOrganizerDto;

import java.util.List;
//TODO Когда появится insert или update методы, то поставить @Valid у их аргументов
public interface IConferenceOrganizerService {

        List<ConferenceOrganizerDto> getOrganizersByConferenceId(int id);

        List<ConferenceOrganizerDto> getConferencesByOrganizerId(int id);

        void remove(int organizerId, int conferenceId);

        void resurrect(int organizerId, int conferenceId);

}
