package fr.orionbs.user_manager.application.service;

import fr.orionbs.user_manager.adapter.persistence.exception.ConflictedUserPersistenceException;
import fr.orionbs.user_manager.adapter.persistence.exception.UnknownAuthorityTypePersistenceException;
import fr.orionbs.user_manager.adapter.persistence.exception.UnknownEventTypePersistenceException;
import fr.orionbs.user_manager.adapter.persistence.exception.UnknownStatusTypePersistenceException;
import fr.orionbs.user_manager.application.port.input.UserRegistrationUseCase;
import fr.orionbs.user_manager.application.port.output.ExistUserPort;
import fr.orionbs.user_manager.application.port.output.InsertUserPort;
import fr.orionbs.user_manager.domain.exception.UserRegistrationException;
import fr.orionbs.user_manager.domain.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRegistrationService implements UserRegistrationUseCase {

    private final InsertUserPort insertUserPort;
    private final ExistUserPort existUserPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User userRegistration(User user, String ip) {


        // We need to call the repository to know if the email is already used.
        if (existUserPort.existUserByEmail(user.getEmail())) {
            log.debug("User already exist. Email: " + user.getEmail());
            throw new UserRegistrationException();
        }

        // We need to encode the password.
        Password password = user.getPasswords().get(0);
        password.setValue(passwordEncoder.encode(password.getValue()));
        password.setMilestone(LocalDateTime.now());

        user.getPasswords().clear();
        user.getPasswords().add(password);

        // We need to set the authority to Member.
        Authority authority = new Authority();
        authority.setAuthorityEnum(AuthorityEnum.MEMBER);
        authority.setMilestone(LocalDateTime.now());

        user.getAuthorities().add(authority);

        // We need to set status.
        Status status = new Status();
        status.setStatusEnum(StatusEnum.UNVERIFIED);
        status.setMilestone(LocalDateTime.now());

        user.getStatuses().add(status);

        // We need to add event.
        Event event = new Event();
        event.setEventEnum(EventEnum.REGISTRATION);
        event.setMilestone(LocalDateTime.now());
        event.setIp(ip);
        event.setResult(Boolean.TRUE);

        user.getEvents().add(event);

        try {

            // We can insert the user through insertion user port.
            return insertUserPort.insertUser(user);

        } catch (ConflictedUserPersistenceException | UnknownStatusTypePersistenceException |
                 UnknownEventTypePersistenceException | UnknownAuthorityTypePersistenceException e) {
            log.debug(e.getMessage());
            throw new UserRegistrationException();
        }

    }
}
