package org.arjunaoverdrive.bookinn.web.payload.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.arjunaoverdrive.bookinn.domain.entities.RoleType;
import org.arjunaoverdrive.bookinn.validation.annotations.Password;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpsertUserRequest {

    @NotBlank
    private String login;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Password
    private String password;
    @NotNull
    private RoleType role;
}
