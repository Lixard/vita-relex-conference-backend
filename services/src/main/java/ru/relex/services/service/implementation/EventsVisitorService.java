package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import ru.relex.db.mapper.EventMapper;
import ru.relex.db.mapper.EventVisitorMapper;
import ru.relex.db.mapper.UserMapper;
import ru.relex.db.model.EventVisitor;
import ru.relex.services.dto.event.EventDto;
import ru.relex.services.dto.shedule.EventVisitorDto;
import ru.relex.services.dto.user.UserAnswerDto;
import ru.relex.services.mapstruct.EventStruct;
import ru.relex.services.mapstruct.EventVisitorStruct;
import ru.relex.services.mapstruct.UserAnswerStruct;
import ru.relex.services.service.IEventVisitorService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class EventsVisitorService implements IEventVisitorService {
    private final EventVisitorMapper eventVisitorMapper;
    private final EventVisitorStruct eventVisitorStruct;
    private final EventMapper eventMapper;
    private final EventStruct eventStruct;
    private final UserMapper userMapper;
    private final UserAnswerStruct userAnswerStruct;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public EventsVisitorService(EventVisitorMapper eventVisitorMapper,
                                EventVisitorStruct eventVisitorStruct,
                                EventMapper eventMapper,
                                EventStruct eventStruct,
                                UserMapper userMapper,
                                UserAnswerStruct userAnswerStruct) {
        this.eventVisitorMapper = eventVisitorMapper;
        this.eventVisitorStruct = eventVisitorStruct;
        this.eventMapper = eventMapper;
        this.eventStruct = eventStruct;
        this.userMapper = userMapper;
        this.userAnswerStruct = userAnswerStruct;
    }

    @Override
    public List<EventDto> getScheduleOfUser(int id) {
        List<EventVisitorDto> eventsOfUser = eventVisitorStruct.toDto(eventVisitorMapper.getScheduleOfUser(id));
        List<EventDto> eventsWithDetails = new ArrayList<>();
        eventsOfUser.forEach(events -> eventsWithDetails.add(eventStruct.toDto(eventMapper.findById(events.getEventId()))));
        return eventsWithDetails;
    }

    @Override
    public List<UserAnswerDto> getVisitorsByEventId(int id) {
        List<EventVisitorDto> eventVisitors = eventVisitorStruct.toDto(eventVisitorMapper.getVisitorsByEventId(id));
        List<UserAnswerDto> users = new ArrayList<>();
        eventVisitors.forEach(visitors -> users.add(userAnswerStruct.toAnswerDto(userMapper.findById(visitors.getUserId()))));
        return users;
    }

    @Override
    public void subscribeOnEvent(@Valid EventVisitorDto eventVisitorDto) {
        EventVisitor eventVisitor = eventVisitorStruct.fromDto(eventVisitorDto);
        if (eventVisitorMapper.findDeletedById(eventVisitor.getUserId(), eventVisitor.getEventId()) != null) {
            eventVisitorMapper.resurrect(eventVisitor.getUserId(), eventVisitor.getEventId());
        } else if (eventVisitorMapper.isExists(eventVisitor.getUserId(), eventVisitor.getEventId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ALREADY_SUBSCRIBED");
        } else {
            eventVisitorMapper.insert(eventVisitor);
        }
    }

    @Override
    public void remove(int userId, int eventId) {
        eventVisitorMapper.delete( userId, eventId);
    }

    @Override
    public void resurrect(int userId, int eventId) {
        eventVisitorMapper.resurrect( userId, eventId);
    }

}
