package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.relex.db.mapper.EventMapper;
import ru.relex.db.model.Event;
import ru.relex.services.dto.event.EventDto;
import ru.relex.services.mapstruct.EventStruct;
import ru.relex.services.service.IEventService;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
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
        return eventStruct.toDto(eventMapper.getEvents());
    }

    @Override
    public EventDto findById(int eventId) {
        return eventStruct.toDto(eventMapper.findById(eventId));
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

    }

    @Override
    public void resurrect(int eventId) {
        eventMapper.resurrect(eventId);
    }

}
