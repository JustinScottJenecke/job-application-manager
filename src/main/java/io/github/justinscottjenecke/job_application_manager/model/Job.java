package io.github.justinscottjenecke.job_application_manager.model;

import io.github.justinscottjenecke.job_application_manager.model.enumerations.WorkModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "job")
public class Job {
    @Id
    @SequenceGenerator(
            name = "job_id_sequence",
            sequenceName = "job_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "job_id_sequence"
    )

    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    private Application application;

    private String position;
    private String company;
    private String location;

    @Enumerated(value = EnumType.STRING)
    private WorkModel workModel; // remote hybrid

    private Double postedSalary;
    private String coreSkill;

    @Column(length = 2000)
    private String requiredSkillsAndTools; // Incoming data will be list or array but will be persisted as comma separated string

    private String jobPostingUrl;
}
