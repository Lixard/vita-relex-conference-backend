package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.db.mapper.EventMapper;
import ru.relex.db.model.Event;
import ru.relex.services.dto.event.EventDto;
import ru.relex.services.mapstruct.EventStruct;
import ru.relex.services.service.IEventService;

import java.util.List;

@Service
public class EventService implements IEventService {
    private EventMapper eventMapper;
    private EventStruct eventStruct;

    @Autowired
    public EventService(EventMapper eventMapper, EventStruct eventStruct) {
        this.eventMapper = eventMapper;
        this.eventStruct = eventStruct;
    }

    @Override
    public List<EventDto> getEvents() {
        List<EventDto> events = eventStruct.toDto(eventMapper.getEvents());
        return events;
    }

    @Override
    public EventDto findById(int eventId) {
        EventDto event = eventStruct.toDto(eventMapper.findById(eventId));
        return event;
    }

    @Override
    public EventDto create(EventDto eventDto) {
        Event event = eventStruct.fromDto(eventDto);
        eventMapper.insert(event);
        return eventStruct.toDto(event);
    }

    @Override
    public EventDto update(EventDto eventDto) {
        Event event = eventStruct.fromDto(eventDto);
        eventMapper.update(event);
        return eventStruct.toDto(event);
    }

    @Override
    public void remove(int eventId) {
        eventMapper.delete(eventId);
    }
}