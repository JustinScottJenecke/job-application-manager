package io.github.justinscottjenecke.job_application_manager.controller;

import io.github.justinscottjenecke.job_application_manager.dto.application.CreateApplicationDto;
import io.github.justinscottjenecke.job_application_manager.model.Application;
import io.github.justinscottjenecke.job_application_manager.model.enumerations.ApplicationStatus;
import io.github.justinscottjenecke.job_application_manager.repository.IApplicationRepository;
import io.github.justinscottjenecke.job_application_manager.repository.IJobRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/application")
public class ApplicationController {

    private final IApplicationRepository applicationRepository;
    private  final IJobRepository jobRepository;

    public ApplicationController(IApplicationRepository applicationRepository, IJobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
    }

    @GetMapping("/test")
    public String test() {
        return "application controller test endpoint";
    }

    @GetMapping
    public List<Application> readAll() {
        return applicationRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateApplicationDto applicationDto) {
        Application application = new Application();

        application.setJob(jobRepository.getReferenceById( applicationDto.jobId() ));
        application.setApplicationStatus( ApplicationStatus.valueOf(applicationDto.applicationStatus()) );
        application.setApplicationStatusNotes(applicationDto.applicationStatusNotes());
        application.setDateApplied(applicationDto.dateApplied());
        application.setCostToCompany(applicationDto.costToCompany());

        applicationRepository.save(application);

        return new ResponseEntity<>("New job applied for.", HttpStatusCode.valueOf(201));
    }

}
