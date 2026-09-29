package org.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class YearGuitarValidator implements ConstraintValidator<ValidYearGuitar, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) return true;

        try {
            return value >= (Year.now().getValue() - 10) && value <= Year.now().getValue();
        } catch (NumberFormatException e) {
            return false;
        }
    }
}