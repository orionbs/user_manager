package fr.orionbs.user_manager.application.service;

import fr.orionbs.user_manager.adapter.persistence.exception.ConflictedUserPersistenceException;
import fr.orionbs.user_manager.adapter.persistence.exception.EmptyPasswordPersistenceException;
import fr.orionbs.user_manager.adapter.persistence.exception.EmptyStatusHistoryPersistenceException;
import fr.orionbs.user_manager.adapter.persistence.exception.UnknownStatusPersistenceException;
import fr.orionbs.user_manager.application.port.input.UserRegistrationUseCase;
import fr.orionbs.user_manager.application.port.output.InsertUserPort;
import fr.orionbs.user_manager.domain.exception.TechnicalException;
import fr.orionbs.user_manager.domain.exception.UserRegistrationException;
import fr.orionbs.user_manager.domain.model.StatusEnum;
import fr.orionbs.user_manager.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService implements UserRegistrationUseCase {

    private final InsertUserPort insertUserPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User userRegistration(User user) {

        try {

            // First, we need to encode the password.
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // Second, we need to set status.
            user.setStatus(StatusEnum.UNVERIFIED.toString());

            // Third, we can insert the user through insertion user port.
            return insertUserPort.insertUser(user);

        } catch (ConflictedUserPersistenceException e) {
            throw new UserRegistrationException();
        } catch (UnknownStatusPersistenceException |
                 EmptyPasswordPersistenceException |
                 EmptyStatusHistoryPersistenceException e) {
            throw new TechnicalException(e.getMessage(), null);
        }
    }
}
