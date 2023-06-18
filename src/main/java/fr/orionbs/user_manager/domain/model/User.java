package fr.orionbs.user_manager.domain.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private List<Password> passwords = new ArrayList<>();
    private List<Status> statuses = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
    private List<Authority> authorities = new ArrayList<>();
}
