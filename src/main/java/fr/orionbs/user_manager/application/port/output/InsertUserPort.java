package fr.orionbs.user_manager.application.port.output;

import fr.orionbs.user_manager.adapter.persistence.exception.ConflictedUserPersistenceException;
import fr.orionbs.user_manager.adapter.persistence.exception.EmptyPasswordPersistenceException;
import fr.orionbs.user_manager.adapter.persistence.exception.EmptyStatusHistoryPersistenceException;
import fr.orionbs.user_manager.adapter.persistence.exception.UnknownStatusPersistenceException;
import fr.orionbs.user_manager.domain.model.User;

public interface InsertUserPort {
    User insertUser(User user) throws ConflictedUserPersistenceException, UnknownStatusPersistenceException, EmptyStatusHistoryPersistenceException, EmptyPasswordPersistenceException;
}
