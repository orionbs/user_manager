package fr.orionbs.user_manager.adapter.persistence.repository;

import fr.orionbs.user_manager.adapter.persistence.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {

    Optional<StatusEntity> findStatusEntityByValueIgnoreCase(String value);

}
