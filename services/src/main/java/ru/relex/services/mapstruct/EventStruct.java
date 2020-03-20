package ru.relex.services.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.relex.db.model.Event;
import ru.relex.services.dto.event.EventDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventStruct {
    @Mapping(target = "details.htmlDescription", source = "htmlDescription")
    @Mapping(target = "details.location", source = "location")
    @Mapping(target = "details.timeStart", source = "timeStart")
    @Mapping(target = "details.timeEnd", source = "timeEnd")
    @Mapping(target = "details.createdBy", source = "createdBy")
    EventDto toDto(Event event);

    @Mapping(target = "htmlDescription", source = "details.htmlDescription")
    @Mapping(target = "location", source = "details.location")
    @Mapping(target = "timeStart", source = "details.timeStart")
    @Mapping(target = "timeEnd", source = "details.timeEnd")
    @Mapping(target = "createdBy", source = "details.createdBy")
    Event fromDto(EventDto eventDto);

    List<EventDto> toDto(List<Event> events);

    List<Event> fromDto(List<EventDto> eventDtos);
}
