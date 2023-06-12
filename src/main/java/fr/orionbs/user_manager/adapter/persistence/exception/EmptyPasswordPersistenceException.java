package fr.orionbs.user_manager.adapter.persistence.exception;

public class EmptyPasswordPersistenceException extends Exception {
    public EmptyPasswordPersistenceException() {
        super("empty-password-persistence-exception");
    }
}
