package fr.orionbs.user_manager.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "status")
@Data
public class StatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "milestone", nullable = false)
    private Timestamp milestone;

    @ManyToOne
    @JoinColumn(name = "status_type_id", referencedColumnName = "id", nullable = false)
    private StatusTypeEntity statusType;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

}
