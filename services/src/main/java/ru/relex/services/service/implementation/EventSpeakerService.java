package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import ru.relex.commons.model.CurrentUser;
import ru.relex.db.mapper.EventMapper;
import ru.relex.db.mapper.EventSpeakerMapper;
import ru.relex.db.mapper.UserMapper;
import ru.relex.db.model.EventSpeaker;
import ru.relex.services.dto.event.EventDto;
import ru.relex.services.dto.speaker.EventSpeakerDto;
import ru.relex.services.dto.user.UserAnswerDto;
import ru.relex.services.mapstruct.EventSpeakerStruct;
import ru.relex.services.mapstruct.EventStruct;
import ru.relex.services.mapstruct.UserAnswerStruct;
import ru.relex.services.service.IEventSpeakerService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class EventSpeakerService implements IEventSpeakerService {
    private final EventSpeakerMapper eventSpeakerMapper;
    private final EventSpeakerStruct eventSpeakerStruct;
    private final EventMapper eventMapper;
    private final EventStruct eventStruct;
    private final UserMapper userMapper;
    private final UserAnswerStruct userAnswerStruct;
    private final CurrentUser currentUser;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public EventSpeakerService(EventSpeakerMapper eventSpeakerMapper,
                               EventSpeakerStruct eventSpeakerStruct,
                               EventMapper eventMapper,
                               EventStruct eventStruct,
                               UserMapper userMapper,
                               UserAnswerStruct userAnswerStruct,
                               CurrentUser currentUser) {
        this.eventSpeakerMapper = eventSpeakerMapper;
        this.eventSpeakerStruct = eventSpeakerStruct;
        this.eventMapper = eventMapper;
        this.eventStruct = eventStruct;
        this.userMapper = userMapper;
        this.userAnswerStruct = userAnswerStruct;
        this.currentUser = currentUser;
    }

    @Override
    public List<UserAnswerDto> getSpeakersByEventId(int id) {
        List<EventSpeakerDto> eventSpeakers = eventSpeakerStruct.toDto(eventSpeakerMapper.getSpeakersByEventId(id));
        List<UserAnswerDto> users = new ArrayList<>();
        eventSpeakers.forEach(visitors -> users.add(userAnswerStruct.toAnswerDto(userMapper.findById(visitors.getUserId()))));
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
        eventSpeaker.setCreatedBy(currentUser.getId());
        if (eventSpeakerMapper.findDeletedById(eventSpeaker.getUserId(), eventSpeaker.getEventId()) != null) {
            eventSpeakerMapper.resurrect(eventSpeaker.getUserId(), eventSpeaker.getEventId());
            eventSpeakerMapper.update(eventSpeaker);
        } else if (eventSpeakerMapper.isExists(eventSpeaker.getUserId(), eventSpeaker.getEventId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RIGHTS_EXIST");
        } else {
            eventSpeakerMapper.insert(eventSpeaker);
        }
    }

    @Override
    public void remove(int speakerId, int eventId) {
        eventSpeakerMapper.delete(speakerId, eventId);
    }

    @Override
    public void resurrect(int speakerId, int eventId) {
        eventSpeakerMapper.resurrect( speakerId, eventId);
    }
}
