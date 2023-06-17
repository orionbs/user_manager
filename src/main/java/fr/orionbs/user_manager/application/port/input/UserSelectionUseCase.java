package fr.orionbs.user_manager.application.port.input;

import fr.orionbs.user_manager.domain.model.User;

import java.util.List;

public interface UserSelectionUseCase {
    User userSelectionById(String uuid);

    List<User> userSelection();
}
