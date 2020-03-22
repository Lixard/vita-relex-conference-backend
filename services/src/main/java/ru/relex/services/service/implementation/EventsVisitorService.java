package ru.relex.services.service.implementation;

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

    @Override
    public List<EventVisitorDto> getScheduleOfUser(int id) {
        List<EventVisitor> eventVisitor = eventVisitorMapper.getScheduleOfUser(id);
        return eventVisitorStruct.toDto(eventVisitor);
    }

    @Override
    public List<EventVisitorDto> getVisitorsByEventId(int id) {
        List<EventVisitor> eventVisitor = eventVisitorMapper.getVisitorsByEventId(id);
        return eventVisitorStruct.toDto(eventVisitor);
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
