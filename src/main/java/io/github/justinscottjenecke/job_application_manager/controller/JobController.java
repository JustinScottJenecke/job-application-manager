package io.github.justinscottjenecke.job_application_manager.controller;

import io.github.justinscottjenecke.job_application_manager.dto.job.CreateJobDto;
import io.github.justinscottjenecke.job_application_manager.dto.job.JobDetailsDto;
import io.github.justinscottjenecke.job_application_manager.model.Job;
import io.github.justinscottjenecke.job_application_manager.service.JobService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody CreateJobDto jobDto) {

        return jobService.create(jobDto) ?
                new ResponseEntity<>("Created new Job: " + jobDto.position() + " at " + jobDto.company(), HttpStatusCode.valueOf(201))
                :
                new ResponseEntity<>("Error creating", HttpStatusCode.valueOf(500));
    }

    @GetMapping()
    public List<Job> readAll() {
        return jobService.readAll();
    }

    @GetMapping("/{id}")
    public Job read(@PathVariable Integer id) {
        return jobService.readById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody JobDetailsDto dto, @PathVariable Integer id) {

        return jobService.updateById(dto, id) ?
            new ResponseEntity<>("Job successfully updated.", HttpStatusCode.valueOf(200))
            :
            new ResponseEntity<>("No Job with " + id + " exists in system.", HttpStatusCode.valueOf(404));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {

        return jobService.deleteById(id) ?
            new ResponseEntity<>("Job successfully deleted.", HttpStatusCode.valueOf(200))
            :
            new ResponseEntity<>("No Job with " + id + " exists in system or error deleting Job.", HttpStatusCode.valueOf(40));
    }
}
