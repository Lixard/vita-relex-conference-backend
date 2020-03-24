package ru.relex.services.service;

import ru.relex.services.dto.conference.ConferenceDto;

import javax.validation.Valid;
import java.util.List;

public interface IConferenceService {
    List<ConferenceDto> getConferences();

    ConferenceDto findById(int conferenceId);

    ConferenceDto create(@Valid ConferenceDto conferenceDto);

    ConferenceDto update(@Valid ConferenceDto conferenceDto);

    void remove(int conferenceId);
}
