package fr.orionbs.user_manager.application.port.input;

import fr.orionbs.user_manager.domain.model.User;

public interface UserAuthenticationUseCase {
    User userAuthentication(User user);
}
