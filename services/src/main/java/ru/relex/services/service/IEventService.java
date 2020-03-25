package ru.relex.services.service;

import ru.relex.services.dto.event.EventDto;

import javax.validation.Valid;
import java.util.List;

public interface IEventService {
    List<EventDto> getEvents();

    EventDto findById(int eventId);

    EventDto create(@Valid EventDto eventDto);

    EventDto update(@Valid EventDto eventDto);

    List<EventDto> getEventsByConferenceId( int conferenceId);

    void resurrect(int eventId);

    void remove(int eventId);

}
