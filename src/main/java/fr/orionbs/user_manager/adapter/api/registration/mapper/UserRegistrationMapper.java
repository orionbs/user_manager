package fr.orionbs.user_manager.adapter.api.registration.mapper;

import fr.orionbs.user_manager.adapter.api.registration.data.UserRegistrationRequest;
import fr.orionbs.user_manager.adapter.api.registration.data.UserRegistrationResponse;
import fr.orionbs.user_manager.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationMapper {

    public User toUser(UserRegistrationRequest userRegistrationRequest) {
        User user = new User();
        user.setEmail(userRegistrationRequest.getEmail());
        user.setPassword(userRegistrationRequest.getPassword());
        user.setFirstName(userRegistrationRequest.getFirstName());
        user.setLastName(userRegistrationRequest.getLastName());
        return user;
    }

    public UserRegistrationResponse toResponse(User user) {
        UserRegistrationResponse userRegistrationResponse = new UserRegistrationResponse();
        userRegistrationResponse.setUuid(user.getUuid());
        return userRegistrationResponse;
    }

}
