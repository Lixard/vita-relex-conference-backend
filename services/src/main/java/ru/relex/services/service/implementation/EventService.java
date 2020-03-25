package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.db.mapper.EventMapper;
import ru.relex.db.mapper.EventSpeakerMapper;
import ru.relex.db.mapper.EventVisitorMapper;
import ru.relex.db.model.Event;
import ru.relex.services.dto.event.EventDto;
import ru.relex.services.mapstruct.EventStruct;
import ru.relex.services.service.IEventService;

import javax.validation.Valid;
import java.util.List;

@Service
public class EventService implements IEventService {
    private EventMapper eventMapper;
    private EventStruct eventStruct;
    private EventVisitorMapper eventVisitorMapper;
    private EventSpeakerMapper eventSpeakerMapper;

    public EventService(EventMapper eventMapper, EventStruct eventStruct, EventVisitorMapper eventVisitorMapper, EventSpeakerMapper eventSpeakerMapper) {
        this.eventMapper = eventMapper;
        this.eventStruct = eventStruct;
        this.eventVisitorMapper = eventVisitorMapper;
        this.eventSpeakerMapper = eventSpeakerMapper;
    }

    @Autowired

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
    public EventDto create(@Valid EventDto eventDto) {
        Event event = eventStruct.fromDto(eventDto);
        eventMapper.insert(event);
        return eventStruct.toDto(event);
    }

    @Override
    public EventDto update(@Valid EventDto eventDto) {
        Event event = eventStruct.fromDto(eventDto);
        eventMapper.update(event);
        return eventStruct.toDto(event);
    }

    @Override
    public List<EventDto> getEventsByConferenceId(int conferenceId) {
        return eventStruct.toDto(eventMapper.getEventsByConferenceId(conferenceId));
    }

    @Override
    public void remove(int eventId) {
        eventMapper.delete(eventId);
        eventSpeakerMapper.deleteByEventId(eventId);
        eventVisitorMapper.deleteByEventId(eventId);

    }

    @Override
    public void resurrect(int eventId) {
        eventMapper.resurrect(eventId);
        eventSpeakerMapper.resurrectByEventId(eventId);
        eventVisitorMapper.resurrectByEventId(eventId);
    }

}
