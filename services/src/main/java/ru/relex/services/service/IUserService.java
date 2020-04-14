package ru.relex.services.service;

import ru.relex.services.dto.user.UserAnswerDto;
import ru.relex.services.dto.user.UserDto;
import ru.relex.services.dto.user.UserPasswordChangeDto;

import javax.validation.Valid;
import java.util.List;

public interface IUserService {
    List<UserAnswerDto> getUsers(String search);

    List<UserAnswerDto> getUsers();

    UserAnswerDto findById(int id);

    UserAnswerDto create(@Valid UserDto userDto);

    UserAnswerDto update(@Valid UserAnswerDto userAnswerDto);

    void updatePassword(@Valid UserPasswordChangeDto userPasswordChangeDto);

    void resurrect(int userId);

    void remove(int userId);


}
