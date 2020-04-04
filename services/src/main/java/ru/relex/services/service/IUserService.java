package ru.relex.services.service;

import ru.relex.services.dto.user.UserAnswerDto;
import ru.relex.services.dto.user.UserDto;

import javax.validation.Valid;
import java.util.List;

public interface IUserService {
    List<UserAnswerDto> findUsers(String search);

    UserAnswerDto findById(int id);

    UserAnswerDto create(@Valid UserDto userDto, String url);

    UserAnswerDto update(@Valid UserDto userDto, String url);

    void resurrect(int userId);

    void remove(int userId);
}
