package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.relex.db.mapper.EventVisitorMapper;
import ru.relex.db.model.EventVisitor;
import ru.relex.services.dto.shedule.EventVisitorDto;
import ru.relex.services.mapstruct.EventVisitorStruct;
import ru.relex.services.service.IEventVisitorService;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class EventsVisitorService implements IEventVisitorService {
    private EventVisitorMapper eventVisitorMapper;
    private EventVisitorStruct eventVisitorStruct;

    @Autowired
    public EventsVisitorService(EventVisitorMapper eventVisitorMapper, EventVisitorStruct eventVisitorStruct) {
        this.eventVisitorMapper = eventVisitorMapper;
        this.eventVisitorStruct = eventVisitorStruct;
    }

    @Override
    public List<EventVisitorDto> getScheduleOfUser(int id) {
        return eventVisitorStruct.toDto(eventVisitorMapper.getScheduleOfUser(id));
    }

    @Override
    public List<EventVisitorDto> getVisitorsByEventId(int id) {
        return eventVisitorStruct.toDto(eventVisitorMapper.getVisitorsByEventId(id));
    }

    @Override
    public void subscribeOnEvent(@Valid EventVisitorDto eventVisitorDto) {
        EventVisitor eventVisitor = eventVisitorStruct.fromDto(eventVisitorDto);
        eventVisitorMapper.insert(eventVisitor);
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
