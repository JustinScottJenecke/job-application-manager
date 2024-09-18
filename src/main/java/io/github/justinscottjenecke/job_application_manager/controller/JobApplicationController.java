package io.github.justinscottjenecke.job_application_manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobApplicationController {

    @GetMapping("/")
    public String test() {
        return "test";
    }
}
