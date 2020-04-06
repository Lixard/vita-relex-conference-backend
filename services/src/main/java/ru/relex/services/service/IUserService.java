package ru.relex.services.service;

import ru.relex.services.dto.user.UserAnswerDto;
import ru.relex.services.dto.user.UserDto;

import javax.validation.Valid;
import java.util.List;

public interface IUserService {
    List<UserAnswerDto> getUsers(String search);

    List<UserAnswerDto> getUsers();

    UserAnswerDto findById(int id);

    UserAnswerDto create(@Valid UserDto userDto);

    UserAnswerDto update(@Valid UserDto userDto);

    void resurrect(int userId);

    void remove(int userId);


}
