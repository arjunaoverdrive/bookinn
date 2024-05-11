package org.arjunaoverdrive.bookinn.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.arjunaoverdrive.bookinn.validation.validators.UniqueUserValidator;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueUserValidator.class)
public @interface UniqueUser {

    String message () default "User with this login and email already exists!";

    Class<?> [] groups() default {};

    Class<? extends Payload> [] payload() default {};
}
