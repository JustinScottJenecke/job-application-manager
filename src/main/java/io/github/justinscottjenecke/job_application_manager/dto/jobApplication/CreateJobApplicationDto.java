package io.github.justinscottjenecke.job_application_manager.dto.jobApplication;

import java.util.Date;

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
