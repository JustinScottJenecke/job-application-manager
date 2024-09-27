package io.github.justinscottjenecke.job_application_manager.controller;

import io.github.justinscottjenecke.job_application_manager.dto.application.CreateApplicationDto;
import io.github.justinscottjenecke.job_application_manager.model.Application;
import io.github.justinscottjenecke.job_application_manager.model.enumerations.ApplicationStatus;
import io.github.justinscottjenecke.job_application_manager.repository.IApplicationRepository;
import io.github.justinscottjenecke.job_application_manager.repository.IJobRepository;
import io.github.justinscottjenecke.job_application_manager.service.ApplicationService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/application")
public class ApplicationController {

    private final IApplicationRepository applicationRepository;
    private  final IJobRepository jobRepository;
    private final ApplicationService applicationService;

    public ApplicationController(
            IApplicationRepository applicationRepository,
            IJobRepository jobRepository,
            ApplicationService applicationService
    ) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.applicationService = applicationService;
    }

    @GetMapping("/test")
    public String test() {
        return "application controller test endpoint";
    }

    @GetMapping
    public List<Application> readAll() {
        return applicationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Application readById(@PathVariable Integer id) {
        return applicationRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("No application found with given id" + id) );
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateApplicationDto applicationDto) {
        Application application = new Application();

        applicationRepository.save(application);

        return new ResponseEntity<>("New job applied for.", HttpStatusCode.valueOf(201));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Application applicationDto, @PathVariable Integer id) {

        //Application x = applicationRepository.getReferenceById(id);

        if(applicationRepository.existsById(id)) {
            Application application = new Application();

            application.setId(id);
            application.setApplicationStatus(applicationDto.getApplicationStatus());
            application.setApplicationStatusNotes(applicationDto.getApplicationStatusNotes());
            application.setCostToCompany(applicationDto.getCostToCompany());
            application.setDateApplied(applicationDto.getDateApplied());
            application.setDateFinalized(applicationDto.getDateFinalized());

            applicationRepository.save(application);
            return new ResponseEntity<>("Job application successfully updated", HttpStatusCode.valueOf(200));
        } else
            return new ResponseEntity<>("No existing application found with given id: " + id, HttpStatusCode.valueOf(404));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws EmptyResultDataAccessException {
        try {
            applicationRepository.deleteById(id);
            return new ResponseEntity<>("Application deleted", HttpStatusCode.valueOf(200));
        } catch (EmptyResultDataAccessException exception) {
            return new ResponseEntity<>("No existing application found with given id: " + id, HttpStatusCode.valueOf(404));
        }
    }
}
