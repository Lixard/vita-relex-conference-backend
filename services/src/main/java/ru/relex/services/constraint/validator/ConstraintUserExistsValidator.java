package ru.relex.services.constraint.validator;

import org.springframework.beans.factory.annotation.Autowired;
import ru.relex.db.mapper.UserMapper;
import ru.relex.services.constraint.annotation.UserExists;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConstraintUserExistsValidator implements ConstraintValidator<UserExists, Integer> {

    private final UserMapper userMapper;

    @Autowired
    public ConstraintUserExistsValidator(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void initialize(UserExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return userMapper.isUserExists(value);
    }
}
