package fr.orionbs.user_manager.adapter.api.registration;

import fr.orionbs.user_manager.adapter.api.registration.data.UserRegistrationRequest;
import fr.orionbs.user_manager.adapter.api.registration.data.UserRegistrationResponse;
import fr.orionbs.user_manager.adapter.api.registration.mapper.UserRegistrationMapper;
import fr.orionbs.user_manager.application.port.input.UserRegistrationUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationApiAdapter {

    private final UserRegistrationUseCase userRegistrationUseCase;
    private final UserRegistrationMapper userRegistrationMapper;

    @PostMapping(path = "registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserRegistrationResponse registration(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest, HttpServletRequest request) {
        return userRegistrationMapper.toResponse(
                userRegistrationUseCase.userRegistration(
                        userRegistrationMapper.toUser(userRegistrationRequest),
                        request.getRemoteAddr()
                )
        );
    }

}
