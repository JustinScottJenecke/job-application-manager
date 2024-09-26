package io.github.justinscottjenecke.job_application_manager.dto.job;

/**
 * Used when displaying in a list of all jobs to Update/manage the jobs in the system
 *
 * @param Id
 * @param position
 * @param company
 * @param location
 * @param workModel WorkModel enumeration
 * @param postedSalary The salary advertised on the job posting or median salary of the salary range
 * @param requiredSkillsAndTools a string of asterisk (*) separated values containing the list of skills, tools required outlined in the job description (Max character 2000)
 * @param jobPostingUrl
 * @param previouslyApplied boolean value that is derived from the applicationStatus field of an associated application
 * @param onGoingApplication boolean value that is derived from the applicationStatus field of an associated application
 */
public record JobDetailsDto(
        Integer Id,
        String position,
        String company,
        String location,
        String workModel,
        Double postedSalary,
        String requiredSkillsAndTools,
        String jobPostingUrl,
        Boolean previouslyApplied, // derived: applicationStatus
        Boolean onGoingApplication // derived: applicationStatus
        ) {
}
