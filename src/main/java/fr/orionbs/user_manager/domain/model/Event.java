package fr.orionbs.user_manager.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Event {
    private Integer id;
    private LocalDateTime milestone;
    private String ip;
    private Boolean result;
    private EventEnum eventEnum;
    private User user;
}
