package fr.orionbs.user_manager.adapter.persistence.mapper;

import fr.orionbs.user_manager.adapter.persistence.entity.StatusEntity;
import org.springframework.stereotype.Component;

@Component
public class StatusPersistenceMapper {

    public String toString(StatusEntity statusEntity) {
        return statusEntity.getValue();
    }

}
