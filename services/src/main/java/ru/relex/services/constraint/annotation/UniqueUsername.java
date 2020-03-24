package ru.relex.services.constraint.annotation;


import ru.relex.services.constraint.validator.ConstraintUniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static ru.relex.services.constraint.ConstraintMessage.Constraint.NOT_UNIQUE;
import static ru.relex.services.constraint.ConstraintMessage.Field.USERNAME;

@Constraint(validatedBy = ConstraintUniqueValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {

    String message() default USERNAME + NOT_UNIQUE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
