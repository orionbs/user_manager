package fr.orionbs.user_manager.adapter.persistence;

import fr.orionbs.user_manager.adapter.persistence.entity.PasswordEntity;
import fr.orionbs.user_manager.adapter.persistence.entity.StatusEntity;
import fr.orionbs.user_manager.adapter.persistence.entity.StatusHistoryEntity;
import fr.orionbs.user_manager.adapter.persistence.entity.UserEntity;
import fr.orionbs.user_manager.adapter.persistence.exception.*;
import fr.orionbs.user_manager.adapter.persistence.mapper.UserPersistenceMapper;
import fr.orionbs.user_manager.adapter.persistence.repository.PasswordRepository;
import fr.orionbs.user_manager.adapter.persistence.repository.StatusHistoryRepository;
import fr.orionbs.user_manager.adapter.persistence.repository.StatusRepository;
import fr.orionbs.user_manager.adapter.persistence.repository.UserRepository;
import fr.orionbs.user_manager.application.port.output.InsertUserPort;
import fr.orionbs.user_manager.application.port.output.SelectUserPort;
import fr.orionbs.user_manager.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements InsertUserPort, SelectUserPort {

    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;
    private final StatusRepository statusRepository;
    private final StatusHistoryRepository statusHistoryRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    @Transactional
    public User insertUser(User user) throws ConflictedUserPersistenceException, UnknownStatusPersistenceException, EmptyStatusHistoryPersistenceException, EmptyPasswordPersistenceException {

        if (userRepository.existsUserEntityByEmailIgnoreCase(user.getEmail())) {
            throw new ConflictedUserPersistenceException();
        } else {

            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(user.getEmail());
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());

            userEntity = userRepository.save(userEntity);

            PasswordEntity passwordEntity = new PasswordEntity();
            passwordEntity.setValue(user.getPassword());
            passwordEntity.setMilestone(Timestamp.from(Instant.now()));
            passwordEntity.setUser(userEntity);

            passwordEntity = passwordRepository.save(passwordEntity);

            StatusEntity statusEntity = statusRepository.findStatusEntityByValueIgnoreCase(user.getStatus())
                    .orElseThrow(UnknownStatusPersistenceException::new);

            StatusHistoryEntity statusHistoryEntity = new StatusHistoryEntity();
            statusHistoryEntity.setMilestone(Timestamp.from(Instant.now()));
            statusHistoryEntity.setStatus(statusEntity);
            statusHistoryEntity.setUser(userEntity);

            statusHistoryEntity = statusHistoryRepository.save(statusHistoryEntity);

            userEntity.getPasswords().add(passwordEntity);
            userEntity.getStatusHistories().add(statusHistoryEntity);

            userEntity = userRepository.save(userEntity);

            return userPersistenceMapper.toUser(userEntity);

        }

    }

    @Override
    @Transactional
    public User selectUserByEmail(String email) throws UnknownUserPersistenceException {
        return userRepository.findUserEntityByEmailIgnoreCase(email)
                .map(userEntity -> {
                    try {
                        return userPersistenceMapper.toUser(userEntity);
                    } catch (EmptyStatusHistoryPersistenceException e) {
                        throw new RuntimeException(e);
                    } catch (EmptyPasswordPersistenceException e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseThrow(UnknownUserPersistenceException::new);
    }
}
