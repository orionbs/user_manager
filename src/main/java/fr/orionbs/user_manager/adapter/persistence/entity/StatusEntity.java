package fr.orionbs.user_manager.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "status")
@Data
public class StatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value", unique = true, nullable = false)
    private String value;

    @OneToMany(mappedBy = "status")
    private List<StatusHistoryEntity> statusHistories = new ArrayList<>();

}
