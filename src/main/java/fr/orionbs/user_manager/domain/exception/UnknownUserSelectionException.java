package fr.orionbs.user_manager.domain.exception;

public class UnknownUserSelectionException extends RuntimeException {

    public UnknownUserSelectionException() {
        super("unknown-user-selection-exception");
    }
}
