package fr.orionbs.user_manager.domain.exception;

public class UserRegistrationException extends RuntimeException {
    public UserRegistrationException() {
        super("user-registration-exception");
    }
}
