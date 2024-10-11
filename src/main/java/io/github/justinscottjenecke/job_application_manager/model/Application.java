package io.github.justinscottjenecke.job_application_manager.model;

import io.github.justinscottjenecke.job_application_manager.model.enumerations.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "application")
public class Application {
    @Id
    @SequenceGenerator(
            name = "application_id_sequence",
            sequenceName = "application_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "application_id_sequence"
    )
    private Integer id;

    @OneToOne(mappedBy = "application", fetch = FetchType.LAZY)
    private Job job;

    private Double costToCompany; // could be moved to profile

    @Enumerated(value = EnumType.STRING)
    private ApplicationStatus applicationStatus; // enum(not applied, applied, no response, interview, assessment, rejected, accepted)

    private String applicationStatusNotes; // metadata on interview number, accepted an offer, etc.
    private Date dateApplied;
    private Date latestStatusUpdate;
    private Date dateFinalized;
}
