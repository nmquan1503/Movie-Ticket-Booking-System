package com.nmquan1503.backend_springboot.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailOrPhoneValidator implements ConstraintValidator<EmailOrPhone, String> {

    private static final String regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$|^(0|\\+84|84)\\d{9}$";

    @Override
    public void initialize(EmailOrPhone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return value.matches(regexp);
    }
}
