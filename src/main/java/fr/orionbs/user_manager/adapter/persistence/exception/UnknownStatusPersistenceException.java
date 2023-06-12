package fr.orionbs.user_manager.adapter.persistence.exception;

public class UnknownStatusPersistenceException extends Exception {
    public UnknownStatusPersistenceException() {
        super("unknown-status-persistence-exception");
    }
}
