package fr.orionbs.user_manager.adapter.api.authentication;

import fr.orionbs.user_manager.adapter.api.authentication.data.UserAuthenticationRequest;
import fr.orionbs.user_manager.adapter.api.authentication.data.UserAuthenticationResponse;
import fr.orionbs.user_manager.adapter.api.authentication.mapper.UserAuthenticationMapper;
import fr.orionbs.user_manager.application.port.input.UserAuthenticationUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationApiAdapter {

    private final UserAuthenticationUseCase userAuthenticationUseCase;
    private final UserAuthenticationMapper userAuthenticationMapper;

    @PostMapping(path = "authentication")
    @ResponseStatus(code = HttpStatus.OK)
    public UserAuthenticationResponse authentication(@Valid @RequestBody UserAuthenticationRequest userAuthenticationRequest) {
        return userAuthenticationMapper.toResponse(
                userAuthenticationUseCase.userAuthentication(
                        userAuthenticationMapper.toUser(userAuthenticationRequest)
                )
        );
    }

}
