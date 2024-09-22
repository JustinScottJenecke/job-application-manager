package io.github.justinscottjenecke.job_application_manager.controller;

import io.github.justinscottjenecke.job_application_manager.dto.job.CreateJobDto;
import io.github.justinscottjenecke.job_application_manager.model.Job;
import io.github.justinscottjenecke.job_application_manager.model.enumerations.WorkModel;
import io.github.justinscottjenecke.job_application_manager.repository.IJobRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {

    private final IJobRepository jobRepository;

    public JobController(IJobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping()
    public List<Job> readAll() {
        return jobRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody CreateJobDto jobDto) {
        Job job = new Job();

        job.setPosition(jobDto.position());
        job.setCompany(jobDto.company());
        job.setJobPostingUrl(jobDto.jobPostingUrl());
        job.setLocation(jobDto.location());
        job.setRequirements(jobDto.requirements());
        job.setOfferedSalary(jobDto.offeredSalary());
        job.setWorkModel( WorkModel.valueOf( jobDto.workModel()) );

        jobRepository.save(job);

        return new ResponseEntity<>("message", HttpStatusCode.valueOf(201));
    }
}
