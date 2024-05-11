package org.arjunaoverdrive.bookinn.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.arjunaoverdrive.bookinn.validation.validators.PasswordValidator;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

    String message () default """
            Password must be from 8 to 16 characters long,
            at least one capital character,
            at least one digit,
            at least one special symbol (!@#&()–:;',?/*~$^+=<>),
            no white space characters!
            """;

    Class<?> [] groups() default {};

    Class<? extends Payload> [] payload() default {};
}
