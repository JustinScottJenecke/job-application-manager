package io.github.justinscottjenecke.job_application_manager.dto.job;

/**
 * Used to create Job instances and add them to a backlog without immediately applying
 * for them
 *
 * @param position
 * @param company
 * @param location
 * @param workModel WorkModel enumeration
 * @param postedSalary The salary advertised on the job posting or median salary of the salary range
 * @param coreSkill The core or primary skill associated with the job
 * @param requiredSkillsAndTools a string of asterisk (*) separated values containing the list of skills, tools required outlined in the job description (Max character 2000)
 * @param jobPostingUrl
 */
public record CreateJobDto(
        String position,
        String company,
        String location,
        String workModel,
        Double postedSalary,
        String coreSkill,
        String requiredSkillsAndTools,
        String jobPostingUrl) {
}