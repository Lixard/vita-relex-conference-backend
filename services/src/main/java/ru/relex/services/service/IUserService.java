package ru.relex.services.service;

import ru.relex.services.dto.user.UserDto;

import javax.validation.Valid;
import java.util.List;

public interface IUserService {
    List<UserDto> findUsers(String search);

    UserDto findById(int id);

    UserDto create(@Valid UserDto userDto);

    UserDto update(@Valid UserDto userDto);

    void remove(int userId);
}
