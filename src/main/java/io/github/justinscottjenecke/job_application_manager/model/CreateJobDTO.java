package io.github.justinscottjenecke.job_application_manager.model;

public record CreateJobDTO(
        String position,
        String company,
        String location,
        String workModel,
        Double offeredSalary,
        String requirements,
        String jobPostingUrl) {
}
