package io.github.justinscottjenecke.job_application_manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Job {
    private Integer id;
    private String position;
    private String company;
    private String location;
    private WorkModel workModel; // remote hybrid
    private Double offeredSalary;
    private String requirements; // Incoming data will be list or array but will be persisted as comma separated string
}
