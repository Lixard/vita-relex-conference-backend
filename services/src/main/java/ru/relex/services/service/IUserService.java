package ru.relex.services.service;

import ru.relex.services.dto.user.UserDto;

import java.util.List;

public interface IUserService {
    List<UserDto> findUsers(String search);

    UserDto findById(int id);

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto);

    void remove(int userId);
}
