package fr.orionbs.user_manager.adapter.exception;

import fr.orionbs.user_manager.domain.exception.UserAuthenticationException;
import fr.orionbs.user_manager.domain.exception.UserRegistrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionApiAdapter {

    private final MessageSource exceptionMessageSource;

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return methodArgumentNotValidException.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
    }

    @ExceptionHandler({UserRegistrationException.class})
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public String handleUserRegistrationException(UserRegistrationException userRegistrationException, Locale locale) {
        return exceptionMessageSource.getMessage(userRegistrationException.getMessage(), null, locale);
    }

    @ExceptionHandler({UserAuthenticationException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleUserAuthenticationException(UserAuthenticationException userAuthenticationException, Locale locale) {
        return exceptionMessageSource.getMessage(userAuthenticationException.getMessage(), null, locale);
    }

}
