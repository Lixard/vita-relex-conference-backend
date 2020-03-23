package ru.relex.services.constraint;

import org.springframework.beans.factory.annotation.Autowired;
import ru.relex.db.mapper.UserMapper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConstraintUniqueValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserMapper userMapper;

    @Autowired
    public ConstraintUniqueValidator(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !userMapper.isUsernameExists(value);
    }
}
