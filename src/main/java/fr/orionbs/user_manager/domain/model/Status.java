package fr.orionbs.user_manager.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Status {
    private UUID uuid;
    private LocalDateTime milestone;
    private StatusEnum statusEnum;
    private User user;
}
