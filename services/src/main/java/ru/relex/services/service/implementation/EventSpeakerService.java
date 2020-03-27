package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.relex.db.mapper.EventMapper;
import ru.relex.db.mapper.EventSpeakerMapper;
import ru.relex.db.mapper.UserMapper;
import ru.relex.db.model.EventSpeaker;
import ru.relex.services.dto.event.EventDto;
import ru.relex.services.dto.speaker.EventSpeakerDto;
import ru.relex.services.dto.user.UserDto;
import ru.relex.services.mapstruct.EventSpeakerStruct;
import ru.relex.services.mapstruct.EventStruct;
import ru.relex.services.mapstruct.UserStruct;
import ru.relex.services.service.IEventSpeakerService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class EventSpeakerService implements IEventSpeakerService {
    private EventSpeakerMapper eventSpeakerMapper;
    private EventSpeakerStruct eventSpeakerStruct;
    private EventMapper eventMapper;
    private EventStruct eventStruct;
    private UserMapper userMapper;
    private UserStruct userStruct;

    @Autowired
    public EventSpeakerService(EventSpeakerMapper eventSpeakerMapper, EventSpeakerStruct eventSpeakerStruct,EventMapper eventMapper,EventStruct eventStruct,  UserStruct userStruct, UserMapper userMapper) {
        this.eventSpeakerMapper = eventSpeakerMapper;
        this.eventSpeakerStruct = eventSpeakerStruct;
        this.eventMapper = eventMapper;
        this.eventStruct = eventStruct;
        this.userMapper = userMapper;
        this.userStruct = userStruct;
    }

    @Override
    public List<UserDto> getSpeakersByEventId(int id) {
        List<EventSpeakerDto> eventSpeakers = eventSpeakerStruct.toDto(eventSpeakerMapper.getSpeakersByEventId(id));
        List<UserDto> users = new ArrayList<>();
        eventSpeakers.forEach(visitors -> users.add(userStruct.toDto(userMapper.findById(visitors.getUserId()))));
        return users;
    }

    @Override
    public List<EventDto> getEventsBySpeakerId(int id) {
        List<EventSpeakerDto> eventsOfSpeaker = eventSpeakerStruct.toDto(eventSpeakerMapper.getEventsBySpeakerId(id));
        List<EventDto> eventsWithDetails = new ArrayList<>();
        eventsOfSpeaker.forEach(events -> eventsWithDetails.add(eventStruct.toDto(eventMapper.findById(events.getEventId()))));
        return eventsWithDetails;
    }

    @Override
    public void assignToEvent(@Valid EventSpeakerDto eventSpeakerDto) {
        EventSpeaker eventSpeaker = eventSpeakerStruct.fromDto(eventSpeakerDto);
        eventSpeakerMapper.insert(eventSpeaker);
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
