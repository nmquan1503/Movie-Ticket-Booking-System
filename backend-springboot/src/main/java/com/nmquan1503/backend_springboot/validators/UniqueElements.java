package com.nmquan1503.backend_springboot.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueElementsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueElements {

    String message() default "DUPLICATE_ELEMENT";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
