package io.github.justinscottjenecke.job_application_manager.service.mappers;

import io.github.justinscottjenecke.job_application_manager.dto.jobApplication.CreateJobApplicationDto;
import io.github.justinscottjenecke.job_application_manager.dto.jobApplication.JobApplicationDto;
import io.github.justinscottjenecke.job_application_manager.model.Application;
import io.github.justinscottjenecke.job_application_manager.model.Job;
import io.github.justinscottjenecke.job_application_manager.model.enumerations.ApplicationStatus;
import io.github.justinscottjenecke.job_application_manager.model.enumerations.WorkModel;
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

    public JobApplicationDto mapCreateJobApplicationToModels(CreateJobApplicationDto dto) {

        var job = new Job();
        job.setPosition( dto.position() );
        job.setCompany(dto.company() );
        job.setLocation( dto.location() );
        job.setWorkModel( WorkModel.valueOf(dto.workModel()) );
        job.setPostedSalary( dto.postedSalary() );
        job.setCoreSkill( dto.coreSkill() );
        job.setRequiredSkillsAndTools( dto.requiredSkillsAndTools() );
        job.setJobPostingUrl( dto.jobPostingUrl() );

        var application = new Application();
        application.setCostToCompany( dto.costToCompany() );
        application.setApplicationStatus( ApplicationStatus.APPLIED );
        application.setApplicationStatusNotes( dto.applicationStatusNotes() );
        application.setDateApplied( dto.dateApplied() );

        // associate entities
        job.setApplication(application);
        application.setJob(job);

        return new JobApplicationDto(job, application);
    }

    public JobApplicationDto mapModelsToJobApplication(Job job, Application application) {
        return new JobApplicationDto(job, application);
    }
}
