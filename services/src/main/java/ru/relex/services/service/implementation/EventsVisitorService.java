package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.db.mapper.EventVisitorMapper;
import ru.relex.db.model.EventVisitor;
import ru.relex.services.dto.shedule.EventVisitorDto;
import ru.relex.services.mapstruct.EventVisitorStruct;
import ru.relex.services.service.IEventVisitorService;

import java.util.List;
@Service
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
    public EventVisitorDto create(EventVisitorDto eventVisitorDto) {
        EventVisitor eventVisitor = eventVisitorStruct.fromDto(eventVisitorDto);
        eventVisitorMapper.insert(eventVisitor);
        return eventVisitorStruct.toDto(eventVisitor);
        }

    @Override
    public EventVisitorDto update(EventVisitorDto eventVisitorDto) {
        return null;
    }
}
