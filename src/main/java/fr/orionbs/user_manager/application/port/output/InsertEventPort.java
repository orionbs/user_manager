package fr.orionbs.user_manager.application.port.output;

import fr.orionbs.user_manager.adapter.persistence.exception.UnknownEventTypePersistenceException;
import fr.orionbs.user_manager.adapter.persistence.exception.UnknownUserPersistenceException;
import fr.orionbs.user_manager.domain.model.Event;

public interface InsertEventPort {
    Event insertEvent(Event event) throws UnknownEventTypePersistenceException, UnknownUserPersistenceException;
}
