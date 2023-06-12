package fr.orionbs.user_manager.adapter.persistence.exception;

public class EmptyStatusHistoryPersistenceException extends Exception {
    public EmptyStatusHistoryPersistenceException() {
        super("empty-status-history-persistence-exception");
    }
}
