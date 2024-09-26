package io.github.justinscottjenecke.job_application_manager.dto.jobApplication;

import java.util.Date;

/**
 * Used to create Job instances, and create a related Application instance for said Job
 * in a single workflow/transaction. Contains all fields of CreateJobDto and CreateApplicationDto
 *
 * @param position
 * @param company
 * @param location
 * @param workModel
 * @param postedSalary
 * @param coreSkill
 * @param requiredSkillsAndTools
 * @param jobPostingUrl
 * @param costToCompany
 * @param applicationStatus
 * @param applicationStatusNotes
 * @param dateApplied
 */
public record CreateJobApplicationDto(
        // job
        String position,
        String company,
        String location,
        String workModel,
        Double postedSalary,
        String coreSkill,
        String requiredSkillsAndTools,
        String jobPostingUrl,
        // application
        Double costToCompany,
        String applicationStatus,
        String applicationStatusNotes,
        Date dateApplied) {
}
