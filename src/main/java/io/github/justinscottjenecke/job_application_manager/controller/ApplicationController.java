package io.github.justinscottjenecke.job_application_manager.controller;

import io.github.justinscottjenecke.job_application_manager.dto.application.CreateApplicationDto;
import io.github.justinscottjenecke.job_application_manager.dto.application.UpdateApplicationDto;
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

    private final ApplicationService applicationService;

    public ApplicationController(
            ApplicationService applicationService
    ) {
        this.applicationService = applicationService;
    }

    @GetMapping("/test")
    public String test() {
        return "application controller test endpoint";
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

        applicationService.create(applicationDto);

        return new ResponseEntity<>("New job applied for.", HttpStatusCode.valueOf(201));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody UpdateApplicationDto applicationDto, @PathVariable Integer id) {

        //Application x = applicationRepository.getReferenceById(id);

        if(applicationService.update(applicationDto, id)) {
            return new ResponseEntity<>("Job application successfully updated", HttpStatusCode.valueOf(200));
        } else
            return new ResponseEntity<>("No existing application found with given id: " + id, HttpStatusCode.valueOf(404));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws EmptyResultDataAccessException {

        var result = applicationService.delete(id);

        if (result) {
            return new ResponseEntity<>("Application deleted", HttpStatusCode.valueOf(200));
        } else {
            return new ResponseEntity<>("No existing application found with given id: " + id, HttpStatusCode.valueOf(404));
        }
    }
}
