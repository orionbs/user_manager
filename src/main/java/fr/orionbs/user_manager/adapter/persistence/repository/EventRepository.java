package fr.orionbs.user_manager.adapter.persistence.repository;

import fr.orionbs.user_manager.adapter.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<EventEntity, UUID> {
}
