package fr.orionbs.user_manager.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "status_type")
@Data
public class StatusTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value", unique = true, nullable = false)
    private String value;

    @OneToMany(mappedBy = "statusType")
    private List<StatusEntity> statuses = new ArrayList<>();

}
