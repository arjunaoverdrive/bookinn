package org.arjunaoverdrive.bookinn.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.arjunaoverdrive.bookinn.validation.validators.DatesAvailableValidator;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Constraint(validatedBy = DatesAvailableValidator.class)
public @interface DatesAvailable {

    String message () default "Specified dates are not available!";

    Class<?> [] groups() default {};

    Class<? extends Payload> [] payload() default {};
}
