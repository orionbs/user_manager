package fr.orionbs.user_manager.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Status {
    private Integer id;
    private LocalDateTime milestone;
    private StatusEnum statusEnum;
    private User user;
}
