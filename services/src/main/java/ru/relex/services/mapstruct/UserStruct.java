package ru.relex.services.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.relex.db.model.User;
import ru.relex.services.dto.user.UserDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserStruct {

    @Mapping(target = "personalInfo.firstName", source = "firstName")
    @Mapping(target = "personalInfo.lastName", source = "lastName")
    @Mapping(target = "personalInfo.email", source = "email")
    UserDto toDto(User user);

    @Mapping(target = "firstName", source = "personalInfo.firstName")
    @Mapping(target = "lastName", source = "personalInfo.lastName")
    @Mapping(target = "email", source = "personalInfo.email")
    User fromDto(UserDto userDto);

    List<UserDto> toDto(List<User> users);

    List<User> fromDto(List<UserDto> userDtos);
}
