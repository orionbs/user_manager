package fr.orionbs.user_manager.adapter.api.authentication.data;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
