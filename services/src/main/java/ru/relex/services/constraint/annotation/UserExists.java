package ru.relex.services.constraint.annotation;

import ru.relex.services.constraint.validator.ConstraintUserExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static ru.relex.services.constraint.ConstraintMessage.Constraint.NOT_EXISTS;
import static ru.relex.services.constraint.ConstraintMessage.Field.USER_ID;

@Constraint(validatedBy = ConstraintUserExistsValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserExists {

    String message() default USER_ID + NOT_EXISTS;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
