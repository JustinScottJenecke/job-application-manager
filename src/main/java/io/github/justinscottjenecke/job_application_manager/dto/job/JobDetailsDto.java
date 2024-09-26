package io.github.justinscottjenecke.job_application_manager.dto.job;

public record JobDetailsDto(
        Integer Id,
        String position,
        String company,
        String location,
        String workModel,
        Double offeredSalary,
        String requirements,
        String jobPostingUrl,
        Boolean previouslyApplied, // derived: applicationStatus
        Boolean onGoingApplication // derived: applicationStatus
        ) {
}
