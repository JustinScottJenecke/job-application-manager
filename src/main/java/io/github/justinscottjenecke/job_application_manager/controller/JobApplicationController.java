package io.github.justinscottjenecke.job_application_manager.controller;

import io.github.justinscottjenecke.job_application_manager.model.Job;
import io.github.justinscottjenecke.job_application_manager.model.WorkModel;
import io.github.justinscottjenecke.job_application_manager.repository.IJobRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobApplicationController {

    private final IJobRepository jobRepository;

    public JobApplicationController(IJobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping("/")
    public String test() {
        return "test";
    }

    @GetMapping("/job/seed")
    public boolean seedJobs() {

        Job savedJob = jobRepository.save(
                new Job(
                    1,
                    null,
                    "Junior Software Developer https://betsoftware.simplify.hr/Vacancy/95516",
                    "BET Software",
                    "Johannesburg, Gauteng",
                    WorkModel.HYBRID,
                    null,
                    "C# * Design Patterns * .NET * SQL",
                    "https://betsoftware.simplify.hr/Vacancy/95516")
        );

        return jobRepository.existsById(savedJob.getId());
    }
}
