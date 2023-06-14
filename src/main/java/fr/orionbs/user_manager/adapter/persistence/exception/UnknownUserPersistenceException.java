package fr.orionbs.user_manager.adapter.persistence.exception;

public class UnknownUserPersistenceException extends Exception {
    public UnknownUserPersistenceException() {
        super("unknown-user-persistence-exception");
    }
}
