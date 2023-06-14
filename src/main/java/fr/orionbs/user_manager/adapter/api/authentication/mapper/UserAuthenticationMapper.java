package fr.orionbs.user_manager.adapter.api.authentication.mapper;

import fr.orionbs.user_manager.adapter.api.authentication.data.UserAuthenticationRequest;
import fr.orionbs.user_manager.adapter.api.authentication.data.UserAuthenticationResponse;
import fr.orionbs.user_manager.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationMapper {

    public User toUser(UserAuthenticationRequest userAuthenticationRequest) {
        User user = new User();
        user.setEmail(userAuthenticationRequest.getEmail());
        user.setPassword(userAuthenticationRequest.getPassword());
        return user;
    }

    public UserAuthenticationResponse toResponse(User user) {
        UserAuthenticationResponse userAuthenticationResponse = new UserAuthenticationResponse();
        userAuthenticationResponse.setUuid(user.getUuid());
        return userAuthenticationResponse;
    }

}