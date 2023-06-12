package fr.orionbs.user_manager.domain.exception;

public class TechnicalException extends RuntimeException {
    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
