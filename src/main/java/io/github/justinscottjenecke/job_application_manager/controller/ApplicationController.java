package io.github.justinscottjenecke.job_application_manager.controller;

import io.github.justinscottjenecke.job_application_manager.dto.application.CreateApplicationDto;
import io.github.justinscottjenecke.job_application_manager.dto.application.UpdateApplicationDto;
import io.github.justinscottjenecke.job_application_manager.model.Application;
import io.github.justinscottjenecke.job_application_manager.service.ApplicationService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-application-manager/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public List<Application> readAll() {
        return applicationService.readAll();
    }

    @GetMapping("/{id}")
    public Application readById(@PathVariable Integer id) {
        return applicationService.read(id);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateApplicationDto applicationDto) {
        return applicationService.create(applicationDto) ?
            new ResponseEntity<>("New job applied for.", HttpStatusCode.valueOf(201))
            :
            new ResponseEntity<>("Error creating new job", HttpStatusCode.valueOf(500));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody UpdateApplicationDto applicationDto, @PathVariable Integer id) {
        return applicationService.update(applicationDto, id) ?
            new ResponseEntity<>("Job application successfully updated", HttpStatusCode.valueOf(200))
            :
            new ResponseEntity<>("No existing application found with given id: " + id, HttpStatusCode.valueOf(404));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws EmptyResultDataAccessException {
        // var successfulDelete = applicationService.delete(id);
        return applicationService.delete(id) ?
            new ResponseEntity<>("Application deleted", HttpStatusCode.valueOf(200))
            :
            new ResponseEntity<>("No existing application found with given id: " + id, HttpStatusCode.valueOf(404));
    }
}