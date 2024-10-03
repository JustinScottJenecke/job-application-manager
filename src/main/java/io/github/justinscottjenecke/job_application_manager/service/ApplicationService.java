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

    /* ========== CRUD Methods ========== */

    /**
     * Accepts a DTO as an argument, maps the DTO to an Application model, and persists the Model to the
     * database. Returns a boolean value if the model has been successfully persisted.
     * @param dto CreateApplicationDto
     * @return Boolean
     */
    public boolean create(CreateApplicationDto dto) {
        return applicationRepository.existsById(applicationRepository.save( mapCreateApplicationToModel(dto) )
                .getId());
    }

    /**
     * Reads an existing job application entity by id. Throws an EntityNotFoundException exception if a Model does not exist
     * for the id given/provided.
     * @param id Integer - id of an existing job application
     * @return Optional<Application>
     */
    public Application read(int id) {
        return applicationRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("No application found with given id" + id) );
    }

    /**
     * Retrieves a list of all job applications persisted in the application's database
     * @return List<Application>
     */
    public List<Application> readAll() {
        return applicationRepository.findAll();
    }

    /**
     * Used to update an existing job application. Accepts an UpdateApplicationDto and maps it to an
     * Application model, before updating the values of the existing Application entity, in the case that
     * the id provided/given matches the id of an entity in the database
     * @param dto UpdateApplicationDto
     * @param id Integer
     * @return Boolean - true for successful update, false for unsuccessful
     */
    public boolean update(UpdateApplicationDto dto, Integer id) {
        if (applicationRepository.existsById(id)) {
            applicationRepository.save( mapUpdateApplicationToModel(dto) );
            return true;
        }

        return false;
    }

    /**
     * Deletes and Application entity by id. Returns true if entity to be deleted does exist and is deleted successfully,
     * otherwise method returns false if the give/provided id does not match any existing Application entity, or if the delete
     * fails for some reason (and the entity still persists in the database)
     * @param id Integer
     * @return boolean - true if delete is successful, false if something went wrong
     */
    public boolean delete(Integer id) {

        // entity does not exist return false to exit method
        if (!applicationRepository.existsById(id)) {
            return false;
        }

        // delete entity
        applicationRepository.deleteById(id);

        // return true if id does not exist
        return !applicationRepository.existsById(id);
    }

    /* ========== Mapper Methods ========== */
    /**
     * Used to update and existing Application.
     * Maps the properties of an ApplicationDetailsDto to an Application Entity.
     * @param dto ApplicationDetailsDto
     * @return Application
     */
    public Application mapUpdateApplicationToModel(UpdateApplicationDto dto) {

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
    public Application mapCreateApplicationToModel(CreateApplicationDto dto) {

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
    public UpdateApplicationDto mapModelToApplicationDetails(Application application) {
        return null;
    }

    /**
     * Maps the properties of an ApplicationDetailsDto to an Application Entity
     * @param application Application
     * @return CreateApplicationDto
     */
    public CreateApplicationDto mapModelToCreateApplication(Application application) {
        return null;
    }
}