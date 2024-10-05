package io.github.justinscottjenecke.job_application_manager.service;

import io.github.justinscottjenecke.job_application_manager.dto.jobApplication.JobApplicationWrapper;
import io.github.justinscottjenecke.job_application_manager.repository.IApplicationRepository;
import io.github.justinscottjenecke.job_application_manager.repository.IJobRepository;
import io.github.justinscottjenecke.job_application_manager.service.mappers.JobApplicationMapper;

public class JobApplicationService {

    private final IJobRepository jobRepository;
    private final IApplicationRepository applicationRepository;
    private final JobApplicationMapper mapper;

    public JobApplicationService(
            IJobRepository jobRepository,
            IApplicationRepository applicationRepository,
            JobApplicationMapper mapper
    ) {
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.mapper = mapper;
    }
}
