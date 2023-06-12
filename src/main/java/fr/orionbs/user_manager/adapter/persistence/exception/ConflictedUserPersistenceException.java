package fr.orionbs.user_manager.adapter.persistence.exception;

public class ConflictedUserPersistenceException extends Exception {
    public ConflictedUserPersistenceException() {
        super("conflicted-user-persistence-exception");
    }
}
