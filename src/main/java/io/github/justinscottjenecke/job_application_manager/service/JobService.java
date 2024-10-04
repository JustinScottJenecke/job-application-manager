package io.github.justinscottjenecke.job_application_manager.service;

import io.github.justinscottjenecke.job_application_manager.dto.job.CreateJobDto;
import io.github.justinscottjenecke.job_application_manager.dto.job.JobDetailsDto;
import io.github.justinscottjenecke.job_application_manager.model.Application;
import io.github.justinscottjenecke.job_application_manager.model.Job;
import io.github.justinscottjenecke.job_application_manager.model.enumerations.WorkModel;
import io.github.justinscottjenecke.job_application_manager.repository.IApplicationRepository;
import io.github.justinscottjenecke.job_application_manager.repository.IJobRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
        return false;
    }

    public Job readById(Integer id) {
        return new Job();
    }

    public List<Job> readAll() {
        return List.of();
    }

    public boolean updateById(JobDetailsDto dto, Integer id) {

        // check if id of job exists
        // check if has application
        // check if application is valid

        return false;
    }

    public boolean deleteById(Integer id) {
        return false;
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

    public void mapModelToJobDetails(Job job) {

    }

}
