package ru.relex.services.service;

import ru.relex.services.dto.shedule.EventVisitorDto;

import java.util.List;

public interface IEventVisitorService {
    List<EventVisitorDto> getScheduleOfUser(int id);

    List<EventVisitorDto> getVisitorsByEventId(int id);

    EventVisitorDto create(EventVisitorDto eventVisitorDto);

    EventVisitorDto update(EventVisitorDto eventVisitorDto);
}
