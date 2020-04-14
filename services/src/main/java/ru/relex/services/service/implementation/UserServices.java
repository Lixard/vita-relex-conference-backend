package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import ru.relex.db.mapper.UserMapper;
import ru.relex.db.model.User;
import ru.relex.services.dto.user.UserAnswerDto;
import ru.relex.services.dto.user.UserDto;
import ru.relex.services.dto.user.UserPasswordChangeDto;
import ru.relex.services.mapstruct.UserAnswerStruct;
import ru.relex.services.mapstruct.UserStruct;
import ru.relex.services.service.IPasswordEncoderService;
import ru.relex.services.service.IUserService;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class UserServices implements IUserService {
    private final UserAnswerStruct userAnswerStruct;
    private UserMapper userMapper;
    private UserStruct userStruct;
    private IPasswordEncoderService passwordEncoderService;

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
    public List<UserAnswerDto> getUsers(String search) {
        return userAnswerStruct.toAnswerDto(userMapper.getAllUsersBySearchOptions(search));
    }

    @Override
    public List<UserAnswerDto> getUsers() {
        return userAnswerStruct.toAnswerDto(userMapper.getAllUsers());
    }

    @Override
    public UserAnswerDto findById(int id) {
        return userAnswerStruct.toAnswerDto(userMapper.findById(id));
    }

    @Override
    public UserAnswerDto create(@Valid UserDto userDto) {
        User user = userStruct.fromDto(userDto);
        user.setPassword(passwordEncoderService.encode(user.getPassword()));
        userMapper.insert(user);
        return userAnswerStruct.toAnswerDto(user);
    }

    @Override
    public UserAnswerDto update(UserAnswerDto userAnswerDto) {
        User user = userAnswerStruct.fromAnswerDto(userAnswerDto);
        userMapper.update(user);
        return userAnswerStruct.toAnswerDto(user);
    }

    @Override
    public void updatePassword(@Valid UserPasswordChangeDto userPasswordChangeDto) {
        User user = userMapper.findById(userPasswordChangeDto.getId());
        if (!passwordEncoderService.matches(userPasswordChangeDto.getOldPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OLDPASSWORD_INCORRECT");
        }
        user.setPassword(passwordEncoderService.encode(userPasswordChangeDto.getNewPassword()));
        userMapper.updatePassword(user);
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
