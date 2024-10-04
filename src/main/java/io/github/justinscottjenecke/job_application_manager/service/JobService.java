package io.github.justinscottjenecke.job_application_manager.service;

import io.github.justinscottjenecke.job_application_manager.dto.job.CreateJobDto;
import io.github.justinscottjenecke.job_application_manager.dto.job.JobDetailsDto;
import io.github.justinscottjenecke.job_application_manager.model.Application;
import io.github.justinscottjenecke.job_application_manager.model.Job;
import io.github.justinscottjenecke.job_application_manager.model.enumerations.WorkModel;
import io.github.justinscottjenecke.job_application_manager.repository.IApplicationRepository;
import io.github.justinscottjenecke.job_application_manager.repository.IJobRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class JobService {

    private final IJobRepository jobRepository;
    private final IApplicationRepository applicationRepository;

    public JobService(IJobRepository jobRepository, IApplicationRepository applicationRepository) {
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
    }

    public boolean create(CreateJobDto dto) {

        var job = jobRepository.save( mapCreateJobToModel(dto) );

        return jobRepository.existsById(job.getId());
    }

    public Job readById(Integer id) {

        return jobRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("No Job entity found with given id: " + id));
    }

    public List<Job> readAll() {
        return jobRepository.findAll();
    }

    public boolean updateById(JobDetailsDto dto, Integer id) {

        // check if id of job exists - check if has application - check if application is valid
        if (jobRepository.existsById(id)) {
            jobRepository.save(  mapJobDetailsToModel(dto) );
            return true;
        }
        return false;
    }

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
