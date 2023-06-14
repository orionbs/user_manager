package fr.orionbs.user_manager.domain.exception;

public class UserAuthenticationException extends RuntimeException {
    public UserAuthenticationException() {
        super("user-authentication-exception");
    }
}
