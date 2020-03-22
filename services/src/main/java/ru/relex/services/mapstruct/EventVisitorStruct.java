package ru.relex.services.mapstruct;

import org.mapstruct.Mapper;
import ru.relex.db.model.EventVisitor;
import ru.relex.services.dto.shedule.EventVisitorDto;


import java.util.List;

@Mapper(componentModel = "spring")
public interface EventVisitorStruct {
    EventVisitorDto toDto(EventVisitor eventVisitor);

    EventVisitor fromDto(EventVisitorDto eventVisitorDto);

    List<EventVisitorDto> toDto(List<EventVisitor> eventVisitor);

    List<EventVisitor> fromDto(List<EventVisitorDto> eventVisitorDto);
}
