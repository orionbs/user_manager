package fr.orionbs.user_manager.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Password {
    private UUID uuid;
    private LocalDateTime milestone;
    private String value;
    private User user;
}
