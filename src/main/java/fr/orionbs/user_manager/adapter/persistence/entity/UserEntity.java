package fr.orionbs.user_manager.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "user")
    private List<PasswordEntity> passwords = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<StatusEntity> statuses = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<EventEntity> events = new ArrayList<>();

}
