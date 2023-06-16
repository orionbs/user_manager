package fr.orionbs.user_manager.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Event {
    private UUID uuid;
    private LocalDateTime milestone;
    private String ip;
    private Boolean result;
    private EventEnum eventEnum;
    private User user;
}
