package ru.relex.services.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.relex.db.model.User;
import ru.relex.services.dto.user.UserAnswerDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAnswerStruct {

    @Mapping(target = "personalInfo.firstName", source = "firstName")
    @Mapping(target = "personalInfo.lastName", source = "lastName")
    @Mapping(target = "personalInfo.email", source = "email")
    UserAnswerDto toAnswerDto(User user);
    
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "firstName", source = "personalInfo.firstName")
    @Mapping(target = "lastName", source = "personalInfo.lastName")
    @Mapping(target = "email", source = "personalInfo.email")
    User fromAnswerDto(UserAnswerDto userAnswerDto);

    List<UserAnswerDto> toAnswerDto(List<User> users);

    List<User> fromAnswerDto(List<UserAnswerDto> userAnswerDto);
}
