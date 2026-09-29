package org.example.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GuitarTypeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGuitarType {

    String message() default "Некорректный тип гитары. Он должен соответствовать одному из следующих: electric, acoustic, bass, classical.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}