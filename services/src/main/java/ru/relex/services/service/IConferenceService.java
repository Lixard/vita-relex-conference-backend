package ru.relex.services.service;

import ru.relex.services.dto.conference.ConferenceDto;

import java.util.List;

public interface IConferenceService {
    List<ConferenceDto> getConferences();

    ConferenceDto findById(int conferenceId);

    ConferenceDto create(ConferenceDto conferenceDto);

    ConferenceDto update(ConferenceDto conferenceDto);

    void remove(int conferenceId);
}
