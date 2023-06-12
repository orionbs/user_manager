package fr.orionbs.user_manager.adapter.api.authentication;

import fr.orionbs.user_manager.adapter.api.authentication.data.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationApiAdapter {

    @PostMapping(path = "authentication")
    @ResponseStatus(code = HttpStatus.CREATED)
    public AuthenticationRequest authentication(AuthenticationRequest authenticationRequest) {
        return authenticationRequest;
    }

}
