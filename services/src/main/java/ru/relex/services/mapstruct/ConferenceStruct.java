package ru.relex.services.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.relex.db.model.Conference;
import ru.relex.db.model.User;
import ru.relex.services.dto.conference.ConferenceDto;
import ru.relex.services.dto.user.UserDto;

import java.sql.Timestamp;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ConferenceStruct {
    @Mapping(target = "details.htmlDescription", source = "htmlDescription")
    @Mapping(target = "details.location", source = "location")
    @Mapping(target = "details.dateStart", source = "dateStart")
    @Mapping(target = "details.dateEnd", source = "dateEnd")
    @Mapping(target = "details.createdAt", source = "createdAt")
    ConferenceDto toDto(Conference conference);

    @Mapping(target = "htmlDescription", source = "details.htmlDescription")
    @Mapping(target = "location", source = "details.location")
    @Mapping(target = "dateStart", source = "details.dateStart")
    @Mapping(target = "dateEnd", source = "details.dateEnd")
    @Mapping(target = "createdAt", source = "details.createdAt")
    Conference fromDto(ConferenceDto conferenceDto);

    List<ConferenceDto> toDto(List<Conference> conferences);

    List<Conference> fromDto(List<ConferenceDto> conferenceDtos);
}
