package fr.orionbs.user_manager.adapter.persistence.mapper;

import fr.orionbs.user_manager.adapter.persistence.entity.StatusEntity;
import fr.orionbs.user_manager.domain.model.Status;
import fr.orionbs.user_manager.domain.model.StatusEnum;
import fr.orionbs.user_manager.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class StatusPersistenceMapper {

    public Status toStatus(StatusEntity statusEntity) {
        Status status = new Status();
        status.setId(statusEntity.getId());
        status.setMilestone(statusEntity.getMilestone().toLocalDateTime());
        status.setStatusEnum(StatusEnum.valueOf(statusEntity.getStatusType().getValue().toUpperCase()));
        User user = new User();
        user.setId(statusEntity.getUser().getId());
        status.setUser(user);
        return status;
    }

}
