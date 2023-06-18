package fr.orionbs.user_manager.adapter.persistence.repository;

import fr.orionbs.user_manager.adapter.persistence.entity.AuthorityTypeEntity;
import fr.orionbs.user_manager.adapter.persistence.entity.EventTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityTypeRepository extends JpaRepository<AuthorityTypeEntity, Integer> {

    Optional<AuthorityTypeEntity> findAuthorityTypeEntityByValueIgnoreCase(String value);
}
