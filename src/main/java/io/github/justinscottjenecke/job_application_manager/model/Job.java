package io.github.justinscottjenecke.job_application_manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn
    private Application application;

    private String position;
    private String company;
    private String location;
    private WorkModel workModel; // remote hybrid
    private Double offeredSalary;
    private String requirements; // Incoming data will be list or array but will be persisted as comma separated string
}
