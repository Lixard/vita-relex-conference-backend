package ru.relex.services.constraint.validator;

import org.springframework.beans.factory.annotation.Autowired;
import ru.relex.db.mapper.EventMapper;
import ru.relex.services.constraint.annotation.EventExists;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConstraintEventExistsValidator implements ConstraintValidator<EventExists, Integer> {

    private final EventMapper eventMapper;

    @Autowired
    public ConstraintEventExistsValidator(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    @Override
    public void initialize(EventExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return eventMapper.isEventExists(value);
    }
}
