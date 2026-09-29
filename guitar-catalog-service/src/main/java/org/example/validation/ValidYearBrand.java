package org.example.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = YearBrandValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidYearBrand {

    String message() default "Некорректный год. Год основания бренда-производителя гитар должен быть между 1833 и текущим годом.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}