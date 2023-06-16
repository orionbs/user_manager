package fr.orionbs.user_manager.adapter.persistence.repository;

import fr.orionbs.user_manager.adapter.persistence.entity.StatusEntity;
import fr.orionbs.user_manager.adapter.persistence.entity.StatusTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusTypeRepository extends JpaRepository<StatusTypeEntity, Integer> {

    Optional<StatusTypeEntity> findStatusTypeEntityByValueIgnoreCase(String value);


}
