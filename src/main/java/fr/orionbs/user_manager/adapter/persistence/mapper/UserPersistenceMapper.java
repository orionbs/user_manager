package fr.orionbs.user_manager.adapter.persistence.mapper;

import fr.orionbs.user_manager.adapter.persistence.entity.PasswordEntity;
import fr.orionbs.user_manager.adapter.persistence.entity.StatusHistoryEntity;
import fr.orionbs.user_manager.adapter.persistence.entity.UserEntity;
import fr.orionbs.user_manager.adapter.persistence.exception.EmptyPasswordPersistenceException;
import fr.orionbs.user_manager.adapter.persistence.exception.EmptyStatusHistoryPersistenceException;
import fr.orionbs.user_manager.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
@RequiredArgsConstructor
public class UserPersistenceMapper {

    private final StatusPersistenceMapper statusPersistenceMapper;
    private final PasswordPersistenceMapper passwordPersistenceMapper;

    public User toUser(UserEntity userEntity) throws EmptyStatusHistoryPersistenceException, EmptyPasswordPersistenceException {
        User user = new User();
        user.setUuid(userEntity.getUuid());
        user.setEmail(userEntity.getEmail());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setStatus(
                statusPersistenceMapper.toString(
                        userEntity.getStatusHistories()
                                .stream()
                                .max(Comparator.comparing(StatusHistoryEntity::getMilestone))
                                .orElseThrow(EmptyStatusHistoryPersistenceException::new)
                                .getStatus()
                )
        );
        user.setPassword(
                passwordPersistenceMapper.toString(
                        userEntity.getPasswords()
                                .stream()
                                .max(Comparator.comparing(PasswordEntity::getMilestone))
                                .orElseThrow(EmptyPasswordPersistenceException::new)
                )
        );
        return user;
    }

}
