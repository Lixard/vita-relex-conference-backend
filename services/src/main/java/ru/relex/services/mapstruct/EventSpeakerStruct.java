package ru.relex.services.mapstruct;

import org.mapstruct.Mapper;
import ru.relex.db.model.Event;
import ru.relex.db.model.EventSpeaker;
import ru.relex.services.dto.event.EventDto;
import ru.relex.services.dto.speaker.EventSpeakerDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventSpeakerStruct {
    EventSpeakerDto toDto(EventSpeaker eventSpeaker);

    EventSpeaker fromDto(EventSpeakerDto eventSpeakerDto);

    List<EventSpeakerDto> toDto(List<EventSpeaker> eventSpeaker);

    List<EventSpeaker> fromDto(List<EventSpeakerDto> eventSpeakerDto);
}
