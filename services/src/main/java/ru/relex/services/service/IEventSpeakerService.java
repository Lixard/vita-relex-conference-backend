package ru.relex.services.service;

import ru.relex.services.dto.event.EventDto;
import ru.relex.services.dto.speaker.EventSpeakerDto;
import ru.relex.services.dto.user.UserAnswerDto;

import javax.validation.Valid;
import java.util.List;

public interface IEventSpeakerService {

    List<UserAnswerDto> getSpeakersByEventId(int id);

    List<EventDto> getEventsBySpeakerId(int id);

    void assignToEvent(@Valid EventSpeakerDto eventSpeakerDto);

    void remove(int speakerId, int eventId);

    void resurrect(int speakerId, int eventId);

}
