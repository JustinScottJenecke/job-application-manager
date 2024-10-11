package io.github.justinscottjenecke.job_application_manager.service;

import io.github.justinscottjenecke.job_application_manager.dto.jobApplication.CreateJobApplicationDto;
import io.github.justinscottjenecke.job_application_manager.dto.jobApplication.JobApplicationDto;
import io.github.justinscottjenecke.job_application_manager.model.Application;
import io.github.justinscottjenecke.job_application_manager.repository.IApplicationRepository;
import io.github.justinscottjenecke.job_application_manager.repository.IJobRepository;
import io.github.justinscottjenecke.job_application_manager.service.mappers.JobApplicationMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

    public boolean create(CreateJobApplicationDto dto) {

        var jobApplicationDto = mapper.mapCreateJobApplicationToModels(dto);

        var job = jobRepository.save(jobApplicationDto.job());
        var application = applicationRepository.save(jobApplicationDto.application());


        return jobRepository.existsById(job.getId()) && applicationRepository.existsById(application.getId());
    }

    public List<JobApplicationDto> readAll() {

        List<Application> allApplications = applicationRepository.findAll();
        List<JobApplicationDto> jobApplications = new ArrayList<>();

        allApplications.forEach(application -> {
            jobApplications.add(new JobApplicationDto(application.getJob(), application));
        });

        return jobApplications;
    }

    public JobApplicationDto readByApplicationId(Integer applicationId) {

//        if (!applicationRepository.existsById(applicationId))
//            throw new EntityNotFoundException("No job applications with id of: " + applicationId);

        var application = applicationRepository.findById(applicationId).orElseThrow(
                () -> new EntityNotFoundException("No job applications with id of: " + applicationId)
        );

        return new JobApplicationDto( application.getJob(), application);
    }
}
