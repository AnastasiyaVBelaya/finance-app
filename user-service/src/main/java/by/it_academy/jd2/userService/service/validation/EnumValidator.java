package by.it_academy.jd2.userService.service.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValidEnum, Enum<?>> {

    private Enum<?>[] enumValues;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {

        enumValues = constraintAnnotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        for (Enum<?> enumValue : enumValues) {
            if (enumValue.name().equals(value.name())) {
                return true;
            }
        }
        return false;
    }
}
