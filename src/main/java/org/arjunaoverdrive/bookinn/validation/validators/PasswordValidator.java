package org.arjunaoverdrive.bookinn.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.arjunaoverdrive.bookinn.validation.annotations.Password;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–:;',?/*~$^+=<>]).{8,20}$");
    }
}
