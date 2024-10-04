package io.github.justinscottjenecke.job_application_manager.service;

import io.github.justinscottjenecke.job_application_manager.dto.job.CreateJobDto;
import io.github.justinscottjenecke.job_application_manager.dto.job.JobDetailsDto;
import io.github.justinscottjenecke.job_application_manager.model.Job;
import io.github.justinscottjenecke.job_application_manager.repository.IJobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class JobService {

    private final IJobRepository jobRepository;

    public JobService(IJobRepository jobRepository) {
        this.jobRepository = jobRepository;
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
        return false;
    }

    public boolean deleteById(Integer id) {
        return false;
    }

    // DTO Mapper functions

    public void mapJobDetailsToModel(JobDetailsDto dto) {

    }

    public void mapCreateJobToModel(CreateJobDto dto) {

    }

    public void mapModelToJobDetails(Job job) {

    }

}
