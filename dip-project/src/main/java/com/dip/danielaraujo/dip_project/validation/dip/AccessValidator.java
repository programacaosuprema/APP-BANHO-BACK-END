package com.dip.danielaraujo.dip_project.validation.dip;

import com.dip.danielaraujo.dip_project.enums.AccessTypeEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccessValidator implements ConstraintValidator<ValidAccess, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        try {
            if ("PÚBLICO".equalsIgnoreCase(value)){
                value = "PUBLIC";
            }else if ("PRIVADO".equalsIgnoreCase(value)){
                value = "PRIVATE";
            }
            AccessTypeEnum accessTypeEnum = AccessTypeEnum.valueOf(value.toUpperCase());
            return accessTypeEnum == AccessTypeEnum.PRIVATE || accessTypeEnum == AccessTypeEnum.PUBLIC;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
