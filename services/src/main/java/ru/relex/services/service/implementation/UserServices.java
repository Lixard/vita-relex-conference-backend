package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.relex.db.mapper.UserMapper;
import ru.relex.db.model.User;
import ru.relex.services.dto.user.UserDto;
import ru.relex.services.mapstruct.UserStruct;
import ru.relex.services.service.IPasswordEncoderService;
import ru.relex.services.service.IUserService;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class UserServices implements IUserService {
    private UserMapper userMapper;
    private UserStruct userStruct;
    private IPasswordEncoderService passwordEncoderService;

    @Autowired
    public UserServices(UserMapper userMapper, UserStruct userStruct, IPasswordEncoderService passwordEncoderService) {
        this.userMapper = userMapper;
        this.userStruct = userStruct;
        this.passwordEncoderService = passwordEncoderService;
    }

    @Override
    public List<UserDto> findUsers(String search) {
        List<User> users = userMapper.getUsers(search);
        return userStruct.toDto(users);
    }

    @Override
    public UserDto findById(int id) {
        UserDto user = userStruct.toDto(userMapper.findById(id));
        return user;
    }

    @Override
    public UserDto create(@Valid UserDto userDto) {
        User user = userStruct.fromDto(userDto);
        user.setPassword(passwordEncoderService.encode(user.getPassword()));
        userMapper.insert(user);
        return userStruct.toDto(user);
    }

    @Override
    public UserDto update(@Valid UserDto userDto) {
        User user = userStruct.fromDto(userDto);
        userMapper.update(user);
        return userStruct.toDto(user);
    }

    @Override
    public void remove(int userId) {
        userMapper.delete(userId);
    }
}
