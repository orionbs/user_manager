package fr.orionbs.user_manager.adapter.persistence.mapper;

import fr.orionbs.user_manager.adapter.persistence.entity.UserEntity;
import fr.orionbs.user_manager.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserPersistenceMapper {

    private final PasswordPersistenceMapper passwordPersistenceMapper;
    private final StatusPersistenceMapper statusPersistenceMapper;
    private final EventPersistenceMapper eventPersistenceMapper;

    public User toUser(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setEmail(userEntity.getEmail());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setPasswords(
                userEntity.getPasswords()
                        .stream()
                        .map(passwordPersistenceMapper::toPassword)
                        .collect(Collectors.toList())
        );
        user.setStatuses(
                userEntity.getStatuses()
                        .stream()
                        .map(statusPersistenceMapper::toStatus)
                        .collect(Collectors.toList())
        );
        user.setEvents(
                userEntity.getEvents()
                        .stream()
                        .map(eventPersistenceMapper::toEvent)
                        .collect(Collectors.toList())
        );
        return user;
    }

}
