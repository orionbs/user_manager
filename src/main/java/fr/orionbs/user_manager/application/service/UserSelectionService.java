package fr.orionbs.user_manager.application.service;

import fr.orionbs.user_manager.adapter.persistence.exception.UnknownUserPersistenceException;
import fr.orionbs.user_manager.application.port.input.UserSelectionUseCase;
import fr.orionbs.user_manager.application.port.output.SelectUserPort;
import fr.orionbs.user_manager.domain.exception.PatternUserSelectionException;
import fr.orionbs.user_manager.domain.exception.UnknownUserSelectionException;
import fr.orionbs.user_manager.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSelectionService implements UserSelectionUseCase {

    private final SelectUserPort selectUserPort;

    @Override
    public User userSelectionById(String id) {

        try {
            // First, we need to convert uuid string to real uuid.
            Integer userId = Integer.valueOf(id);

            // Second, we can select the user by his uuid.
            return selectUserPort.selectUserById(userId);

        } catch (NumberFormatException e) {
            throw new PatternUserSelectionException();
        } catch (UnknownUserPersistenceException e) {
            throw new UnknownUserSelectionException();
        }


    }

    @Override
    public List<User> userSelection() {
        return selectUserPort.selectUsers();
    }
}
