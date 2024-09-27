package io.github.justinscottjenecke.job_application_manager.service;

import io.github.justinscottjenecke.job_application_manager.App;
import io.github.justinscottjenecke.job_application_manager.dto.application.ApplicationDetailsDto;
import io.github.justinscottjenecke.job_application_manager.dto.application.CreateApplicationDto;
import io.github.justinscottjenecke.job_application_manager.model.Application;
import io.github.justinscottjenecke.job_application_manager.model.enumerations.ApplicationStatus;
import io.github.justinscottjenecke.job_application_manager.repository.IApplicationRepository;
import io.github.justinscottjenecke.job_application_manager.repository.IJobRepository;
import io.github.justinscottjenecke.job_application_manager.service.mappers.ApplicationMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ApplicationService {

    private final IApplicationRepository applicationRepository;
    private  final IJobRepository jobRepository;

    public ApplicationService(
            IApplicationRepository applicationRepository,
            IJobRepository jobRepository
    ) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
    }

    /**
     * Used to update and existing Application.
     * Maps the properties of an ApplicationDetailsDto to an Application Entity.
     * @param dto ApplicationDetailsDto
     * @return Application
     */
    public static Application applicationDetailsToApplication(ApplicationDetailsDto dto) {

        var application = new Application();

        application.set

        return null;
    }

    /**
     * Used to create a brand new Application Entity and sets its default values not provided by the Dto.
     * Maps the properties of an ApplicationDetailsDto to an Application Entity
     * @param dto CreateApplicationDto
     * @return Application
     */
    public Application createApplicationToApplication(CreateApplicationDto dto) {

        var application = new Application();

        application.setJob(jobRepository.getReferenceById( dto.jobId() ));
        application.setCostToCompany(dto.costToCompany());
        application.setApplicationStatus(ApplicationStatus.APPLIED);
        application.setApplicationStatusNotes(dto.applicationStatusNotes());
        application.setDateApplied(new Date());
        application.setLatestStatusUpdate(new Date());

        return application;
    }

    /**
     * Maps the properties of an Application to an ApplicationDetailsDto Dto
     * @param application Application
     * @return ApplicationDetailsDto
     */
    public ApplicationDetailsDto applicationToApplicationDetails(Application application) {
        return null;
    }

    /**
     * Maps the properties of an ApplicationDetailsDto to an Application Entity
     * @param application Application
     * @return CreateApplicationDto
     */
    public CreateApplicationDto applicationToCreateApplication(Application application) {
        return null;
    }
}