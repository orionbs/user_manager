package fr.orionbs.user_manager.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Authority {
    private Integer id;
    private LocalDateTime milestone;
    private AuthorityEnum authorityEnum;
    private User user;
}
