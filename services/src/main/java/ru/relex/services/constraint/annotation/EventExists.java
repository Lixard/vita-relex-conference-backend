package ru.relex.services.constraint.annotation;

import ru.relex.services.constraint.validator.ConstraintEventExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static ru.relex.services.constraint.ConstraintMessage.Constraint.NOT_EXISTS;
import static ru.relex.services.constraint.ConstraintMessage.Field.EVENT_ID;

@Constraint(validatedBy = ConstraintEventExistsValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventExists {

    String message() default EVENT_ID + NOT_EXISTS;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
