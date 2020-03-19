package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.relex.db.mapper.UserMapper;
import ru.relex.db.model.User;
import ru.relex.services.dto.user.UserDto;
import ru.relex.services.mapstruct.UserStruct;
import ru.relex.services.service.IUserService;

import java.util.List;

public class UserServices implements IUserService {
    private UserMapper userMapper;
    private UserStruct userStruct;

    @Autowired
    public UserServices(UserMapper userMapper, UserStruct userStruct) {
        this.userMapper = userMapper;
        this.userStruct = userStruct;
    }

    @Override
    public List<UserDto> findUsers(String search) {
        List<User> users = userMapper.getUsers(search);
        return userStruct.toDto(users);
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = userStruct.fromDto(userDto);
        userMapper.insert(user);
        return userStruct.toDto(user);
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = userStruct.fromDto(userDto);
        userMapper.update(user);
        return userStruct.toDto(user);
    }

    @Override
    public void remove(int userId) {
        userMapper.delete(userId);
    }
}
