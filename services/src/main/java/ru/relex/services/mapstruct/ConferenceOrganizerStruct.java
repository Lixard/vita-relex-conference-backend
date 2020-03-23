package ru.relex.services.mapstruct;

import org.mapstruct.Mapper;
import ru.relex.db.model.ConferenceOrganizer;
import ru.relex.services.dto.organizer.ConferenceOrganizerDto;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ConferenceOrganizerStruct {

    ConferenceOrganizerDto toDto(ConferenceOrganizer conferenceOrganizer);

    ConferenceOrganizer fromDto(ConferenceOrganizerDto conferenceOrganizerDto);

    List<ConferenceOrganizerDto> toDto(List<ConferenceOrganizer> conferenceOrganizer);

    List<ConferenceOrganizer> fromDto(List<ConferenceOrganizerDto> conferenceOrganizerDto);
}
