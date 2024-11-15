package com.keniu.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to validate that two fields have the same value.
 */
@Constraint(validatedBy = FieldMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMatch {

    /**
     * The first field to compare.
     */
    String first();

    /**
     * The second field to compare.
     */
    String second();

    /**
     * The error message if the fields do not match.
     */
    String message() default "Fields don't match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
