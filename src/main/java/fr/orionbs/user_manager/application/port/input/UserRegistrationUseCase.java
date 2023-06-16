package fr.orionbs.user_manager.application.port.input;

import fr.orionbs.user_manager.domain.model.User;

public interface UserRegistrationUseCase {
    User userRegistration(User user, String ip);
}
