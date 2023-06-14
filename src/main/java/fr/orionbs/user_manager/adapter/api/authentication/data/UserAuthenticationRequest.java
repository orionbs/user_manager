package fr.orionbs.user_manager.adapter.api.authentication.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserAuthenticationRequest {

    @NotBlank(message = "{user-authentication-request.email.not-blank}")
    @Email(message = "{user-authentication-request.email.email}")
    private String email;

    @NotBlank(message = "{user-authentication-request.password.not-blank}")
    @Pattern(message = "{user-authentication-request.password.pattern}", regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
    private String password;

}
