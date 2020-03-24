package ru.relex.services.service;

import ru.relex.services.dto.speaker.EventSpeakerDto;

import java.util.List;

public interface IEventSpeakerService {

    List<EventSpeakerDto> getSpeakersByEventId(int id);

    List<EventSpeakerDto> getEventsBySpeakerId(int id);

    EventSpeakerDto create(EventSpeakerDto eventSpeakerDto);

    void remove(int speakerId, int eventId);

    void resurrect(int speakerId, int eventId);

}
