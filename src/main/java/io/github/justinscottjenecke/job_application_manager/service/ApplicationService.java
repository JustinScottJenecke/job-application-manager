package io.github.justinscottjenecke.job_application_manager.service;

import io.github.justinscottjenecke.job_application_manager.dto.application.CreateApplicationDto;
import io.github.justinscottjenecke.job_application_manager.dto.application.UpdateApplicationDto;
import io.github.justinscottjenecke.job_application_manager.model.Application;
import io.github.justinscottjenecke.job_application_manager.model.enumerations.ApplicationStatus;
import io.github.justinscottjenecke.job_application_manager.repository.IApplicationRepository;
import io.github.justinscottjenecke.job_application_manager.repository.IJobRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    /* CRUD Methods */

    public void create(CreateApplicationDto dto) {
        applicationRepository.save( createApplicationToModel(dto) );
    }

    public Application read(int id) {
        return applicationRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("No application found with given id" + id) );
    }

    public List<Application> readAll() {
        return  applicationRepository.findAll();
    }

    public boolean update(UpdateApplicationDto dto, Integer id) {
        if (applicationRepository.existsById(id)) {
            applicationRepository.save( updateApplicationToModel(dto) );
            return true;
        }

        return false;
    }

    public boolean delete(Integer id) {

        // entity does not exist
        if (!applicationRepository.existsById(id)) {
            return false;
        }
        applicationRepository.deleteById(id);

        // return true if id does not exist
        return !applicationRepository.existsById(id);
    }

    /* Mapper Methods */
    /**
     * Used to update and existing Application.
     * Maps the properties of an ApplicationDetailsDto to an Application Entity.
     * @param dto ApplicationDetailsDto
     * @return Application
     */
    public Application updateApplicationToModel(UpdateApplicationDto dto) {

        var application = new Application();

        application.setId(dto.id());
        application.setJob( jobRepository.getReferenceById( dto.jobId() ));
        application.setCostToCompany(dto.costToCompany());
        application.setApplicationStatus( ApplicationStatus.valueOf(dto.applicationStatus()) );
        application.setApplicationStatusNotes(dto.applicationStatusNotes());
        application.setDateApplied(dto.dateApplied());
        application.setLatestStatusUpdate(dto.latestStatusUpdate());
        application.setDateFinalized(dto.dateFinalized());

        return application;
    }

    /**
     * Used to create a brand new Application Entity and sets its default values not provided by the Dto.
     * Maps the properties of an ApplicationDetailsDto to an Application Entity
     * @param dto CreateApplicationDto
     * @return Application
     */
    public Application createApplicationToModel(CreateApplicationDto dto) {

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
    public UpdateApplicationDto modelToApplicationDetails(Application application) {
        return null;
    }

    /**
     * Maps the properties of an ApplicationDetailsDto to an Application Entity
     * @param application Application
     * @return CreateApplicationDto
     */
    public CreateApplicationDto modelToCreateApplication(Application application) {
        return null;
    }
}