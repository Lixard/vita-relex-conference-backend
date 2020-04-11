package ru.relex.services.service;

import ru.relex.services.dto.event.EventDto;
import ru.relex.services.dto.shedule.EventVisitorDto;
import ru.relex.services.dto.user.UserAnswerDto;

import javax.validation.Valid;
import java.util.List;

public interface IEventVisitorService {
    List<EventDto> getScheduleOfUser(int id);

    List<UserAnswerDto> getVisitorsByEventId(int id);

    void subscribeOnEvent(@Valid EventVisitorDto eventVisitorDto);

    void remove(int userId, int eventId);

    void resurrect(int userId, int eventId);
}
