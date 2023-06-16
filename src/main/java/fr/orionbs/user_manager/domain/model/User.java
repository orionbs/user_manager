package fr.orionbs.user_manager.domain.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class User {
    private UUID uuid;
    private String email;
    private String firstName;
    private String lastName;
    private List<Password> passwords = new ArrayList<>();
    private List<Status> statuses = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
}
