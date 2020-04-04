package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.relex.db.mapper.UserMapper;
import ru.relex.db.model.User;
import ru.relex.services.dto.user.UserAnswerDto;
import ru.relex.services.dto.user.UserDto;
import ru.relex.services.mapstruct.UserAnswerStruct;
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
    private final UserAnswerStruct userAnswerStruct;

    @Autowired
    public UserServices(UserMapper userMapper,
                        UserStruct userStruct,
                        IPasswordEncoderService passwordEncoderService,
                        UserAnswerStruct userAnswerStruct) {
        this.userMapper = userMapper;
        this.userStruct = userStruct;
        this.passwordEncoderService = passwordEncoderService;
        this.userAnswerStruct = userAnswerStruct;
    }

    @Override
    public List<UserAnswerDto> findUsers(String search) {
        return userAnswerStruct.toAnswerDto(userMapper.getUsers(search));
    }

    @Override
    public UserAnswerDto findById(int id) {
        return userAnswerStruct.toAnswerDto(userMapper.findById(id));
    }

    @Override
    public UserAnswerDto create(@Valid UserDto userDto, String url) {
        User user = userStruct.fromDto(userDto);
        user.setPassword(passwordEncoderService.encode(user.getPassword()));
        userMapper.insert(user);
        userMapper.updateUserImage(userDto.getUserId(),url);
        return userAnswerStruct.toAnswerDto(user);
    }

    @Override
    public UserAnswerDto update(@Valid UserDto userDto, String url) {
        User user = userStruct.fromDto(userDto);
        userMapper.update(user);
        userMapper.updateUserImage(userDto.getUserId(),url);
        return userAnswerStruct.toAnswerDto(user);
    }

    @Override
    public void remove(int userId) {
        userMapper.delete(userId);
    }

    @Override
    public void resurrect(int userId) {
        userMapper.resurrect(userId);
    }
}
