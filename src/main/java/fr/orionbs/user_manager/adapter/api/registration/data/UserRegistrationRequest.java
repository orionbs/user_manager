package fr.orionbs.user_manager.adapter.api.registration.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRegistrationRequest {

    @NotBlank(message = "{user-registration-request.email.not-blank}")
    @Email(message = "{user-registration-request.email.email}")
    private String email;

    @NotBlank(message = "{user-registration-request.password.not-blank}")
    @Pattern(message = "{user-registration-request.password.pattern}", regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
    private String password;

    @NotBlank(message = "{user-registration-request.first-name.not-blank}")
    private String firstName;

    @NotBlank(message = "{user-registration-request.last-name.not-blank}")
    private String lastName;

}
