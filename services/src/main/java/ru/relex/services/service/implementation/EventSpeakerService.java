package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.db.mapper.EventSpeakerMapper;
import ru.relex.db.model.EventSpeaker;
import ru.relex.services.dto.speaker.EventSpeakerDto;
import ru.relex.services.mapstruct.EventSpeakerStruct;
import ru.relex.services.service.IEventSpeakerService;

import java.util.List;

@Service
public class EventSpeakerService implements IEventSpeakerService {
    private EventSpeakerMapper eventSpeakerMapper;
    private EventSpeakerStruct eventSpeakerStruct;

    @Autowired
    public EventSpeakerService(EventSpeakerMapper eventSpeakerMapper, EventSpeakerStruct eventSpeakerStruct) {
        this.eventSpeakerMapper = eventSpeakerMapper;
        this.eventSpeakerStruct = eventSpeakerStruct;
    }

    @Override
    public List<EventSpeakerDto> getSpeakersByEventId(int id) {
        List<EventSpeaker> eventSpeaker = eventSpeakerMapper.getSpeakersByEventId(id);
        return eventSpeakerStruct.toDto(eventSpeaker);
    }

    @Override
    public List<EventSpeakerDto> getEventsBySpeakerId(int id) {
        List<EventSpeaker> eventSpeaker = eventSpeakerMapper.getEventsBySpeakerId(id);
        return eventSpeakerStruct.toDto(eventSpeaker);
    }

    @Override
    public EventSpeakerDto create(EventSpeakerDto eventSpeakerDto) {
        EventSpeaker eventSpeaker = eventSpeakerStruct.fromDto(eventSpeakerDto);
        eventSpeakerMapper.insert(eventSpeaker);
        return eventSpeakerStruct.toDto(eventSpeaker);
    }

    @Override
    public void remove(int speakerId, int eventId) {
        eventSpeakerMapper.resurrect( speakerId, eventId);
    }

    @Override
    public void resurrect(int speakerId, int eventId) {
        eventSpeakerMapper.resurrect( speakerId, eventId);
    }
}
