package fr.orionbs.user_manager.adapter.persistence.repository;

import fr.orionbs.user_manager.adapter.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsUserEntityByEmailIgnoreCase(String email);

    Optional<UserEntity> findUserEntityByEmailIgnoreCase(String email);

    Optional<UserEntity> findUserEntityById(Integer id);

}
