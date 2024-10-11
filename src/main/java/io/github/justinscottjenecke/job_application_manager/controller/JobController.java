package io.github.justinscottjenecke.job_application_manager.controller;

import io.github.justinscottjenecke.job_application_manager.dto.job.CreateJobDto;
import io.github.justinscottjenecke.job_application_manager.dto.job.JobDetailsDto;
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

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody CreateJobDto jobDto) {
        return new ResponseEntity<>("create", HttpStatusCode.valueOf(201));
    }

    @GetMapping()
    public List<Job> readAll() {
        return null;
    }

    @GetMapping("/{id}")
    public Job read(@PathVariable Integer id) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody JobDetailsDto dto, @PathVariable Integer id) {
        return new ResponseEntity<>("update", HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return new ResponseEntity<>("delete", HttpStatusCode.valueOf(200));
    }
}
