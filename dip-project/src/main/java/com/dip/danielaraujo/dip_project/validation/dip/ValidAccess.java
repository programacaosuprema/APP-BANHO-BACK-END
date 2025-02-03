package com.dip.danielaraujo.dip_project.validation.dip;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AccessValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAccess {
    String message() default "tipo de acesso inválido!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
