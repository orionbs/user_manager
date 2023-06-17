package fr.orionbs.user_manager.adapter.persistence;

import fr.orionbs.user_manager.adapter.persistence.entity.EventEntity;
import fr.orionbs.user_manager.adapter.persistence.entity.EventTypeEntity;
import fr.orionbs.user_manager.adapter.persistence.entity.UserEntity;
import fr.orionbs.user_manager.adapter.persistence.exception.UnknownEventTypePersistenceException;
import fr.orionbs.user_manager.adapter.persistence.exception.UnknownUserPersistenceException;
import fr.orionbs.user_manager.adapter.persistence.mapper.EventPersistenceMapper;
import fr.orionbs.user_manager.adapter.persistence.repository.EventRepository;
import fr.orionbs.user_manager.adapter.persistence.repository.EventTypeRepository;
import fr.orionbs.user_manager.adapter.persistence.repository.UserRepository;
import fr.orionbs.user_manager.application.port.output.InsertEventPort;
import fr.orionbs.user_manager.domain.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class EventPersistenceAdapter implements InsertEventPort {

    private final EventRepository eventRepository;
    private final EventTypeRepository eventTypeRepository;
    private final UserRepository userRepository;
    private final EventPersistenceMapper eventPersistenceMapper;

    @Override
    @Transactional
    public Event insertEvent(Event event) throws UnknownEventTypePersistenceException, UnknownUserPersistenceException {

        EventEntity eventEntity = new EventEntity();
        eventEntity.setMilestone(Timestamp.valueOf(event.getMilestone()));
        eventEntity.setResult(event.getResult());
        eventEntity.setIp(event.getIp());

        EventTypeEntity eventTypeEntity = eventTypeRepository.findEventTypeEntityByValueIgnoreCase(event.getEventEnum().toString())
                .orElseThrow(UnknownEventTypePersistenceException::new);

        eventEntity.setEventType(eventTypeEntity);

        UserEntity userEntity = userRepository.findUserEntityById(event.getUser().getId())
                .orElseThrow(UnknownUserPersistenceException::new);

        eventEntity.setUser(userEntity);

        return eventPersistenceMapper.toEvent(eventRepository.save(eventEntity));
    }
}
