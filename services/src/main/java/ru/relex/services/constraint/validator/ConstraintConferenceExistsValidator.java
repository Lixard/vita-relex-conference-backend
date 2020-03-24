package ru.relex.services.constraint.validator;

import org.springframework.beans.factory.annotation.Autowired;
import ru.relex.db.mapper.ConferenceMapper;
import ru.relex.services.constraint.annotation.ConferenceExists;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConstraintConferenceExistsValidator implements ConstraintValidator<ConferenceExists, Integer> {

    private final ConferenceMapper conferenceMapper;

    @Autowired
    public ConstraintConferenceExistsValidator(ConferenceMapper conferenceMapper) {
        this.conferenceMapper = conferenceMapper;
    }

    @Override
    public void initialize(ConferenceExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
      return conferenceMapper.isConferenceExists(value);
    }
}
