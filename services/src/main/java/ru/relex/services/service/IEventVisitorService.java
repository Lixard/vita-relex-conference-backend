package ru.relex.services.service;

import ru.relex.services.dto.shedule.EventVisitorDto;

import javax.validation.Valid;
import java.util.List;

public interface IEventVisitorService {
    List<EventVisitorDto> getScheduleOfUser(int id);

    List<EventVisitorDto> getVisitorsByEventId(int id);

    EventVisitorDto create(@Valid EventVisitorDto eventVisitorDto);

    EventVisitorDto update(@Valid EventVisitorDto eventVisitorDto);
}
