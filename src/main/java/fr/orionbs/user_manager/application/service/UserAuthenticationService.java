package fr.orionbs.user_manager.application.service;

import fr.orionbs.user_manager.adapter.persistence.exception.UnknownEventTypePersistenceException;
import fr.orionbs.user_manager.adapter.persistence.exception.UnknownUserPersistenceException;
import fr.orionbs.user_manager.application.port.input.UserAuthenticationUseCase;
import fr.orionbs.user_manager.application.port.output.InsertEventPort;
import fr.orionbs.user_manager.application.port.output.SelectUserPort;
import fr.orionbs.user_manager.domain.exception.UserAuthenticationException;
import fr.orionbs.user_manager.domain.model.Event;
import fr.orionbs.user_manager.domain.model.EventEnum;
import fr.orionbs.user_manager.domain.model.Password;
import fr.orionbs.user_manager.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService implements UserAuthenticationUseCase {

    private final SelectUserPort selectUserPort;
    private final InsertEventPort insertEventPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User userAuthentication(User user, String ip) {

        try {

            // First, we need to find the user.
            User userFound = selectUserPort.selectUserByEmail(user.getEmail());

            // Second, we need get the last password of the user.
            Password lastPassword = userFound.getPasswords()
                    .stream()
                    .max(Comparator.comparing(Password::getMilestone))
                    .orElseThrow(UserAuthenticationException::new);

            // Third, we need to initiate an event.
            Event event = new Event();
            event.setMilestone(LocalDateTime.now());
            event.setEventEnum(EventEnum.AUTHENTICATION);
            event.setUser(userFound);
            event.setIp(ip);

            if (passwordEncoder.matches(user.getPasswords().get(0).getValue(), lastPassword.getValue())) {

                event.setResult(Boolean.TRUE);
                insertEventPort.insertEvent(event);
                return userFound;

            } else {
                event.setResult(Boolean.FALSE);
                insertEventPort.insertEvent(event);
                throw new UserAuthenticationException();
            }

        } catch (UnknownUserPersistenceException | UnknownEventTypePersistenceException e) {
            throw new UserAuthenticationException();
        }

    }

}
