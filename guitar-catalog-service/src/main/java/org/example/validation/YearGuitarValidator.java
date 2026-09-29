package org.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class YearGuitarValidator implements ConstraintValidator<ValidYearGutar, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) return true;

        try {
            int year = Integer.parseInt(value);
            return year >= (Year.now().getValue() - 10) && year <= Year.now().getValue();
        } catch (NumberFormatException e) {
            return false;
        }
    }
}