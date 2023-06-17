package fr.orionbs.user_manager.adapter.persistence.mapper;

import fr.orionbs.user_manager.adapter.persistence.entity.PasswordEntity;
import fr.orionbs.user_manager.domain.model.Password;
import fr.orionbs.user_manager.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class PasswordPersistenceMapper {

    public Password toPassword(PasswordEntity passwordEntity) {
        Password password = new Password();
        password.setId(passwordEntity.getId());
        password.setMilestone(passwordEntity.getMilestone().toLocalDateTime());
        password.setValue(passwordEntity.getValue());
        User user = new User();
        user.setId(passwordEntity.getUser().getId());
        password.setUser(user);
        return password;
    }

}
