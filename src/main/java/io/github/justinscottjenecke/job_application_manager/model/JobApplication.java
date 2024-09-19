package io.github.justinscottjenecke.job_application_manager.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class JobApplication {
    private Integer id;
    private String position;
    private String company;
    private String location;
    private String workModel; // remote hybrid
    private Double offeredSalary;
    private Double costToCompany;
    private List<String> requiredSkills;
    private String applicationStatus; // enum(not applied, applied, no response, interview, assessment, rejected, accepted)
    private String jobPostUrl;
    private Date dateApplied;
}
