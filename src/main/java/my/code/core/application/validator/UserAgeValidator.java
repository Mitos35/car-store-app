package my.code.core.application.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserAgeValidator implements ConstraintValidator<UserAgeConstraint, Integer> {

    @Override
    public boolean isValid(Integer userAge, ConstraintValidatorContext context) {
        return userAge > 0;
    }
}

