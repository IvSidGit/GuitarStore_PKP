package org.example.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = YearGuitarValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidYearGuitar {

    String message() default "Некорректный год. Год выпуска гитары должен быть между 2000 и текущим годом.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}