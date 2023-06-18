package fr.orionbs.user_manager.application.port.output;

import fr.orionbs.user_manager.adapter.persistence.exception.*;
import fr.orionbs.user_manager.domain.model.User;

public interface InsertUserPort {
    User insertUser(User user) throws ConflictedUserPersistenceException, UnknownStatusTypePersistenceException, UnknownEventTypePersistenceException, UnknownAuthorityTypePersistenceException;
}
