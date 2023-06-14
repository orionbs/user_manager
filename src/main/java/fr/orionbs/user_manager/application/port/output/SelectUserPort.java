package fr.orionbs.user_manager.application.port.output;

import fr.orionbs.user_manager.adapter.persistence.exception.UnknownUserPersistenceException;
import fr.orionbs.user_manager.domain.model.User;

public interface SelectUserPort {
    User selectUserByEmail(String email) throws UnknownUserPersistenceException;
}
