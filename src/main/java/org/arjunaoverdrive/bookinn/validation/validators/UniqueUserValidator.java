package org.arjunaoverdrive.bookinn.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.arjunaoverdrive.bookinn.domain.dao.UserRepository;
import org.arjunaoverdrive.bookinn.validation.annotations.UniqueUser;
import org.arjunaoverdrive.bookinn.web.payload.user.UpsertUserRequest;

@RequiredArgsConstructor
public class UniqueUserValidator implements ConstraintValidator<UniqueUser, UpsertUserRequest> {

    private final UserRepository userRepository;
    @Override
    public boolean isValid(UpsertUserRequest request, ConstraintValidatorContext context) {
        return !userRepository.existsByLoginAndEmail(request.getLogin(), request.getEmail());
    }
}
