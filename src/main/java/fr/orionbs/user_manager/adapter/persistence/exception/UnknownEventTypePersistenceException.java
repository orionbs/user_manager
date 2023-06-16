package fr.orionbs.user_manager.adapter.persistence.exception;

public class UnknownEventTypePersistenceException extends UnknownPersistenceException {
    public UnknownEventTypePersistenceException() {
        super("unknown-event-type-persistence-exception");
    }
}
