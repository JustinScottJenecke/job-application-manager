package io.github.justinscottjenecke.job_application_manager.model;

import java.util.Date;

public class Application {
    private Integer id;
    private Double costToCompany; // could be moved to profile
    private ApplicationStatus applicationStatus; // enum(not applied, applied, no response, interview, assessment, rejected, accepted)
    private String applicationStatusNotes; // metadata on interview number, accepted an offer, etc.
    private String jobPostUrl;
    private Date dateApplied;
    private Date dateFinalized;
}
