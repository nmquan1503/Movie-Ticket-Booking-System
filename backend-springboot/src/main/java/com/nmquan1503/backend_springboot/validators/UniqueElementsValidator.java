package com.nmquan1503.backend_springboot.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.*;

public class UniqueElementsValidator implements ConstraintValidator<UniqueElements, Collection<?>> {

    @Override
    public void initialize(UniqueElements constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Collection<?> elements, ConstraintValidatorContext constraintValidatorContext) {
        if (elements == null) {
            return true;
        }
        Set<Object> seen = new HashSet<>();
        for (Object element : elements) {
            if (!seen.add(element)) {
                return false;
            }
        }
        return true;
    }
}
