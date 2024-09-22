package io.github.justinscottjenecke.job_application_manager.controller;

import io.github.justinscottjenecke.job_application_manager.model.Application;
import io.github.justinscottjenecke.job_application_manager.repository.IApplicationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/application")
public class ApplicationController {

    private final IApplicationRepository applicationRepository;

    public ApplicationController(IApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @GetMapping("/test")
    public String test() {
        return "application controller test endpoint";
    }

    @GetMapping
    public List<Application> readAll() {
        return applicationRepository.findAll();
    }

}
