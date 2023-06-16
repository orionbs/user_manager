package fr.orionbs.user_manager.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "event_type")
@Data
public class EventTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value", unique = true, nullable = false)
    private String value;

}
