package fr.orionbs.user_manager.application.service;

import fr.orionbs.user_manager.adapter.persistence.exception.UnknownUserPersistenceException;
import fr.orionbs.user_manager.application.port.input.UserAuthenticationUseCase;
import fr.orionbs.user_manager.application.port.output.SelectUserPort;
import fr.orionbs.user_manager.domain.exception.UserAuthenticationException;
import fr.orionbs.user_manager.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService implements UserAuthenticationUseCase {

    private final SelectUserPort selectUserPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User userAuthentication(User user) {

        try {

            User userFound = selectUserPort.selectUserByEmail(user.getEmail());

            if (passwordEncoder.matches(user.getPassword(), userFound.getPassword())) {

                userFound.setPassword(null);

                return userFound;

            } else {
                throw new UserAuthenticationException();
            }

        } catch (UnknownUserPersistenceException e) {
            throw new UserAuthenticationException();
        }

    }

}
