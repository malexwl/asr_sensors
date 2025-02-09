package com.asr.sensors.dto.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ProperRangeValidator.class)
public @interface ProperRange {
    String message() default "{ProperRange.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
