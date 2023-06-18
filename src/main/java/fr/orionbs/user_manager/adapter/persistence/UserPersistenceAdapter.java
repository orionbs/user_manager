package fr.orionbs.user_manager.adapter.persistence;

import fr.orionbs.user_manager.adapter.persistence.entity.*;
import fr.orionbs.user_manager.adapter.persistence.exception.*;
import fr.orionbs.user_manager.adapter.persistence.mapper.UserPersistenceMapper;
import fr.orionbs.user_manager.adapter.persistence.repository.*;
import fr.orionbs.user_manager.application.port.output.ExistUserPort;
import fr.orionbs.user_manager.application.port.output.InsertUserPort;
import fr.orionbs.user_manager.application.port.output.SelectUserPort;
import fr.orionbs.user_manager.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements InsertUserPort, SelectUserPort, ExistUserPort {

    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;
    private final StatusRepository statusRepository;
    private final StatusTypeRepository statusTypeRepository;
    private final UserPersistenceMapper userPersistenceMapper;
    private final EventTypeRepository eventTypeRepository;
    private final EventRepository eventRepository;
    private final AuthorityTypeRepository authorityTypeRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public User insertUser(User user) throws ConflictedUserPersistenceException, UnknownStatusTypePersistenceException, UnknownEventTypePersistenceException, UnknownAuthorityTypePersistenceException {

        if (userRepository.existsUserEntityByEmailIgnoreCase(user.getEmail())) {
            throw new ConflictedUserPersistenceException();
        } else {

            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(user.getEmail());
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());

            userEntity = userRepository.save(userEntity);

            Password password = user.getPasswords().get(0);

            PasswordEntity passwordEntity = new PasswordEntity();
            passwordEntity.setValue(password.getValue());
            passwordEntity.setMilestone(Timestamp.valueOf(password.getMilestone()));
            passwordEntity.setUser(userEntity);

            passwordEntity = passwordRepository.save(passwordEntity);

            Authority authority = user.getAuthorities().get(0);

            AuthorityTypeEntity authorityTypeEntity = authorityTypeRepository.findAuthorityTypeEntityByValueIgnoreCase(authority.getAuthorityEnum().toString())
                    .orElseThrow(UnknownAuthorityTypePersistenceException::new);

            AuthorityEntity authorityEntity = new AuthorityEntity();
            authorityEntity.setMilestone(Timestamp.valueOf(authority.getMilestone()));
            authorityEntity.setAuthorityType(authorityTypeEntity);
            authorityEntity.setUser(userEntity);

            authorityEntity = authorityRepository.save(authorityEntity);

            Status status = user.getStatuses().get(0);

            StatusTypeEntity statusTypeEntity = statusTypeRepository.findStatusTypeEntityByValueIgnoreCase(status.getStatusEnum().toString())
                    .orElseThrow(UnknownStatusTypePersistenceException::new);

            StatusEntity statusEntity = new StatusEntity();
            statusEntity.setMilestone(Timestamp.valueOf(status.getMilestone()));
            statusEntity.setStatusType(statusTypeEntity);
            statusEntity.setUser(userEntity);

            statusEntity = statusRepository.save(statusEntity);

            Event event = user.getEvents().get(0);

            EventTypeEntity eventTypeEntity = eventTypeRepository.findEventTypeEntityByValueIgnoreCase(event.getEventEnum().toString())
                    .orElseThrow(UnknownEventTypePersistenceException::new);

            EventEntity eventEntity = new EventEntity();
            eventEntity.setMilestone(Timestamp.valueOf(event.getMilestone()));
            eventEntity.setIp(event.getIp());
            eventEntity.setResult(event.getResult());
            eventEntity.setEventType(eventTypeEntity);
            eventEntity.setUser(userEntity);

            eventEntity = eventRepository.save(eventEntity);

            userEntity.getPasswords().add(passwordEntity);
            userEntity.getAuthorities().add(authorityEntity);
            userEntity.getStatuses().add(statusEntity);
            userEntity.getEvents().add(eventEntity);

            userEntity = userRepository.save(userEntity);

            return userPersistenceMapper.toUser(userEntity);

        }

    }

    @Override
    @Transactional
    public User selectUserByEmail(String email) throws UnknownUserPersistenceException {
        return userRepository.findUserEntityByEmailIgnoreCase(email)
                .map(userPersistenceMapper::toUser)
                .orElseThrow(UnknownUserPersistenceException::new);
    }

    @Override
    @Transactional
    public User selectUserById(Integer id) throws UnknownUserPersistenceException {
        return userRepository.findUserEntityById(id)
                .map(userPersistenceMapper::toUser)
                .orElseThrow(UnknownUserPersistenceException::new);
    }

    @Override
    @Transactional
    public List<User> selectUsers() {
        return userRepository.findAll()
                .stream()
                .map(userPersistenceMapper::toUser)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean existUserByEmail(String email) {
        return userRepository.existsUserEntityByEmailIgnoreCase(email);
    }
}
