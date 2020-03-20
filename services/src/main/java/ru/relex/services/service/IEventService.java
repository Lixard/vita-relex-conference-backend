package ru.relex.services.service;

import ru.relex.services.dto.event.EventDto;

import java.util.List;

public interface IEventService {
    List<EventDto> getEvents();

    EventDto findById(int eventId);

    EventDto create(EventDto eventDto);

    EventDto update(EventDto eventDto);

    void remove(int eventId);

}
