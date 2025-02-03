package com.dip.danielaraujo.dip_project.validation.image;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileTypeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFileType {
    String message() default "Tipo de arquivo inválido!"; // Mensagem de erro padrão
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
