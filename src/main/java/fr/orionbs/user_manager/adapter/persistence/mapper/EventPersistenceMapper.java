package fr.orionbs.user_manager.adapter.persistence.mapper;

import fr.orionbs.user_manager.adapter.persistence.entity.EventEntity;
import fr.orionbs.user_manager.domain.model.Event;
import fr.orionbs.user_manager.domain.model.EventEnum;
import fr.orionbs.user_manager.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class EventPersistenceMapper {

    public Event toEvent(EventEntity eventEntity) {
        Event event = new Event();
        event.setUuid(eventEntity.getUuid());
        event.setMilestone(eventEntity.getMilestone().toLocalDateTime());
        event.setEventEnum(EventEnum.valueOf(eventEntity.getEventType().getValue().toUpperCase()));
        event.setIp(eventEntity.getIp());
        event.setResult(eventEntity.isResult());
        User user = new User();
        user.setUuid(eventEntity.getUser().getUuid());
        event.setUser(user);
        return event;
    }

}
