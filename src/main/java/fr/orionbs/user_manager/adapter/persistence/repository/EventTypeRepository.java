package fr.orionbs.user_manager.adapter.persistence.repository;

import fr.orionbs.user_manager.adapter.persistence.entity.EventTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventTypeRepository extends JpaRepository<EventTypeEntity, Integer> {

    Optional<EventTypeEntity> findEventTypeEntityByValueIgnoreCase(String value);
}
