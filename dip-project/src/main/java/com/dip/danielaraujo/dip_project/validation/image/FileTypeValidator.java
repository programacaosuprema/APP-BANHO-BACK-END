package com.dip.danielaraujo.dip_project.validation.image;

import com.dip.danielaraujo.dip_project.enums.FileTypeEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileTypeValidator implements ConstraintValidator<ValidFileType, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        try {
            FileTypeEnum fileTypeEnum = FileTypeEnum.valueOf(value.toUpperCase());
            return fileTypeEnum == FileTypeEnum.PNG || fileTypeEnum == FileTypeEnum.JPG;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
