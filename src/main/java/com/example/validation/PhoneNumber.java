
/*
 * Copyright (C) 2025 Archie L. Cobbs. All rights reserved.
 */

package com.example.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Validation constraint for North American Numbering Plan (10 digit) phone numbers.
 */
@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = PhoneNumber.PATTERN)
@ReportAsSingleViolation
public @interface PhoneNumber {

    String PATTERN = "[2-9][0-9]{2}[2-9][0-9]{2}[0-9]{4}";

    String message() default "Invalid phone number \"${validatedValue}\"; must be of the form NXXNXXXXXX";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
