package fr.orionbs.user_manager.adapter.persistence.repository;

import fr.orionbs.user_manager.adapter.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsUserEntityByEmailIgnoreCase(String email);

}
