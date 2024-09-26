package io.github.justinscottjenecke.job_application_manager.dto.job;

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