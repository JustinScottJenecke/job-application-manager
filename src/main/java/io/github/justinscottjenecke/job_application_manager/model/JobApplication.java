package io.github.justinscottjenecke.job_application_manager.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class JobApplication {
    // Job
    private Integer id;
    private String position;
    private String company;
    private String location;
    private WorkModel workModel; // remote hybrid
    private Double offeredSalary;
    private String requirements; // Incoming data will be list or array but will be persisted as comma separated string
    // Application
    private Double costToCompany; // could be moved to profile
    private ApplicationStatus applicationStatus; // enum(not applied, applied, no response, interview, assessment, rejected, accepted)
    private String applicationStatusNotes; // metadata on interview number, accepted an offer, etc.
    private String jobPostUrl;
    private Date dateApplied;
    private Date dateFinalized;
}
