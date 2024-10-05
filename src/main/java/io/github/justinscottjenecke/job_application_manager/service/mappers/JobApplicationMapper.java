package io.github.justinscottjenecke.job_application_manager.service.mappers;

import io.github.justinscottjenecke.job_application_manager.dto.jobApplication.JobApplicationDto;
import io.github.justinscottjenecke.job_application_manager.model.Application;
import io.github.justinscottjenecke.job_application_manager.model.Job;
import io.github.justinscottjenecke.job_application_manager.repository.IApplicationRepository;
import io.github.justinscottjenecke.job_application_manager.repository.IJobRepository;
import org.springframework.stereotype.Component;

@Component
public class JobApplicationMapper {

    private final IJobRepository jobRepository;
    private final IApplicationRepository applicationRepository;

    public JobApplicationMapper(IApplicationRepository applicationRepository, IJobRepository jobRepository) {
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
    }

    public boolean mapCreateJobApplicationToModels(JobApplicationDto dto) {

        var job = new Job();
        var application = new Application();

        return false;
    }

    public JobApplicationDto mapModelsToJobApplication(Job job, Application application) {
        return new JobApplicationDto(null, null);
    }
}
