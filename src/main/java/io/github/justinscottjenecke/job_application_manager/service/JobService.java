package io.github.justinscottjenecke.job_application_manager.service;

import io.github.justinscottjenecke.job_application_manager.dto.job.CreateJobDto;
import io.github.justinscottjenecke.job_application_manager.dto.job.JobDetailsDto;
import io.github.justinscottjenecke.job_application_manager.model.Job;
import io.github.justinscottjenecke.job_application_manager.model.enumerations.WorkModel;
import io.github.justinscottjenecke.job_application_manager.repository.IApplicationRepository;
import io.github.justinscottjenecke.job_application_manager.repository.IJobRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final IJobRepository jobRepository;
    private final IApplicationRepository applicationRepository;

    public JobService(IJobRepository jobRepository, IApplicationRepository applicationRepository) {
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
    }

    /**
     * Creates and persists a new Job entity into the database
     * @param dto CreateJobDto
     * @return Job
     */
    public boolean create(CreateJobDto dto) {

        var job = jobRepository.save( mapCreateJobToModel(dto) );

        return jobRepository.existsById(job.getId());
    }

    /**
     * Loads a single Job entity from the database using an id as a search parameter.
     * Throws an EntityNotFound Exception if the id does not match the id of any existing Job entity
     * @param id Integer
     * @return Job
     */
    public Job readById(Integer id) {

        return jobRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("No Job entity found with given id: " + id));
    }

    /**
     * Returns a List of all Job entities stored within the Database
     * @return List<Job>
     */
    public List<Job> readAll() {
        return jobRepository.findAll();
    }

    /**
     * Updates any existing Job entity with properties of an incoming Dto's fields.
     * If the given id does not match the id of any Job entities in the database, the method
     * returns false, otherwise the method returns true upon a successful update.
     *
     * @param dto JobDetailsDto
     * @param id Integer
     * @return Boolean
     */
    public boolean updateById(JobDetailsDto dto, Integer id) {

        // check if id of job exists - check if has application - check if application is valid
        if (jobRepository.existsById(id)) {
            jobRepository.save(  mapJobDetailsToModel(dto) );
            return true;
        }
        return false;
    }

    /**
     * Deletes a Job entity by id. Returns false in the event that a Job is unsuccessfully deleted
     * or if the id given goes not match the id of any entity in the system.
     * @param id Integer
     * @return Boolean
     */
    public boolean deleteById(Integer id) {

        if (!jobRepository.existsById(id))
            return false;

        jobRepository.deleteById(id);

        return !jobRepository.existsById(id);
    }

    // DTO Mapper functions

    /**
     * Maps an incoming JobDetailsDto to a Job model. Typically, runs when updating an existing job
     * @param dto JobDetailsDto
     * @return Job
     */
    public Job mapJobDetailsToModel(JobDetailsDto dto) {

        var job = new Job();

        job.setId(dto.id());
        job.setPosition(dto.position());
        job.setCompany(dto.company());
        job.setLocation(dto.location());
        job.setWorkModel( WorkModel.valueOf(dto.workModel()) );
        job.setPostedSalary(dto.postedSalary());
        job.setCoreSkill(dto.coreSkill());
        job.setRequiredSkillsAndTools(dto.requiredSkillsAndTools());
        job.setJobPostingUrl(dto.jobPostingUrl());

        // only set application if job has been applied for
        if (dto.onGoingApplication())
            job.setApplication( applicationRepository.findById(dto.id()).orElseThrow() );

        return job;
    }

    public Job mapCreateJobToModel(CreateJobDto dto) {

        var job = new Job();

        job.setPosition(dto.position());
        job.setCompany(dto.company());
        job.setLocation(dto.location());
        job.setWorkModel( WorkModel.valueOf(dto.workModel()) );
        job.setPostedSalary(dto.postedSalary());
        job.setCoreSkill(dto.coreSkill());
        job.setRequiredSkillsAndTools(dto.requiredSkillsAndTools());
        job.setJobPostingUrl(dto.jobPostingUrl());

        return job;
    }

    public JobDetailsDto mapModelToJobDetails(Job job) {

        if(job.getApplication() != null) {
            return new JobDetailsDto(
                    job.getId(),
                    job.getPosition(),
                    job.getCompany(),
                    job.getLocation(),
                    job.getWorkModel().toString(),
                    job.getPostedSalary(),
                    job.getCoreSkill(),
                    job.getRequiredSkillsAndTools(),
                    job.getJobPostingUrl(),
                    null,
                    true,
                    job.getApplication().getId()
            );
        } else {
            return new JobDetailsDto(
                    job.getId(),
                    job.getPosition(),
                    job.getCompany(),
                    job.getLocation(),
                    job.getWorkModel().toString(),
                    job.getPostedSalary(),
                    job.getCoreSkill(),
                    job.getRequiredSkillsAndTools(),
                    job.getJobPostingUrl(),
                    null,
                    false,
                    null
            );
        }
    }

}
