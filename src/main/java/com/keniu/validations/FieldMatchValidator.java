package com.keniu.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator implementation for the {@code @FieldMatch} annotation.
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private static final int FIRST_CHAR_INDEX = 0;
    private static final int MIN_LENGTH = 1;

    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            String firstValue = (String) value.getClass().getMethod("get"
                    + capitalize(firstFieldName)).invoke(value);
            String secondValue = (String) value.getClass().getMethod("get"
                    + capitalize(secondFieldName)).invoke(value);
            boolean isValid = (firstValue == null && secondValue == null)
                    || (firstValue != null && firstValue.equals(secondValue));

            if (!isValid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(secondFieldName)
                        .addConstraintViolation();
            }
            return isValid;
        } catch (Exception e) {
            return false;
        }
    }

    private String capitalize(String fieldName) {
        return fieldName.substring(FIRST_CHAR_INDEX, MIN_LENGTH + FIRST_CHAR_INDEX).toUpperCase()
            + fieldName.substring(MIN_LENGTH);
    }
}
