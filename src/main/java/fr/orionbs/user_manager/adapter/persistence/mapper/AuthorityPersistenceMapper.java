package fr.orionbs.user_manager.adapter.persistence.mapper;

import fr.orionbs.user_manager.adapter.persistence.entity.AuthorityEntity;
import fr.orionbs.user_manager.adapter.persistence.entity.EventEntity;
import fr.orionbs.user_manager.domain.model.*;
import org.springframework.stereotype.Component;

@Component
public class AuthorityPersistenceMapper {

    public Authority toAuthority(AuthorityEntity authorityEntity) {
        Authority authority = new Authority();
        authority.setId(authorityEntity.getId());
        authority.setMilestone(authorityEntity.getMilestone().toLocalDateTime());
        authority.setAuthorityEnum(AuthorityEnum.valueOf(authorityEntity.getAuthorityType().getValue().toUpperCase()));
        User user = new User();
        user.setId(authorityEntity.getUser().getId());
        authority.setUser(user);
        return authority;
    }

}
