package fr.orionbs.user_manager.adapter.persistence.mapper;

import fr.orionbs.user_manager.adapter.persistence.entity.PasswordEntity;
import org.springframework.stereotype.Component;

@Component

public class PasswordPersistenceMapper {

    public String toString(PasswordEntity passwordEntity) {
        return passwordEntity.getValue();
    }
}
