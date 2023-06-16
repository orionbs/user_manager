package fr.orionbs.user_manager.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "event")
@Data
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "milestone", nullable = false)
    private Timestamp milestone;

    @Column(name = "ip", nullable = false)
    private String ip;

    @Column(name = "result", nullable = false)
    private boolean result;

    @ManyToOne
    @JoinColumn(name = "event_type_id", referencedColumnName = "id", nullable = false)
    private EventTypeEntity eventType;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uuid", nullable = false)
    private UserEntity user;

}
