package org.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class GuitarTypeValidator implements ConstraintValidator<ValidGuitarType, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<String> types = List.of("electric", "acoustic", "bass", "classical");
        if (value == null || value.isBlank()) return true;
        return types.contains(value);
    }
}
