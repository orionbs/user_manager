package fr.orionbs.user_manager.domain.model;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private UUID uuid;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String status;
}
