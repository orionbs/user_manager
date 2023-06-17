package fr.orionbs.user_manager.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Password {
    private Integer id;
    private LocalDateTime milestone;
    private String value;
    private User user;
}
