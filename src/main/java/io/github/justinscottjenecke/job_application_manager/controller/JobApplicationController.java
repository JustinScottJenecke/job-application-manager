package io.github.justinscottjenecke.job_application_manager.controller;

import io.github.justinscottjenecke.job_application_manager.dto.jobApplication.CreateJobApplicationDto;
import io.github.justinscottjenecke.job_application_manager.dto.jobApplication.JobApplicationDto;
import io.github.justinscottjenecke.job_application_manager.service.JobApplicationService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }


    @PostMapping
    public ResponseEntity<String> create(CreateJobApplicationDto dto) {

        return jobApplicationService.create(dto) ?
                new ResponseEntity<>("New Job: " + dto.position() + " at " + dto.company() + " created and applied for.", HttpStatusCode.valueOf(201))
                :
                new ResponseEntity<>("Error new job application. Double check payload", HttpStatusCode.valueOf(500));
    }

    @GetMapping("{/id")
    public JobApplicationDto read(@PathVariable Integer id) {
        return jobApplicationService.readByApplicationId(id);
    }

    @GetMapping()
    public List<JobApplicationDto> readAll() {
        return jobApplicationService.readAll();
    }
}
