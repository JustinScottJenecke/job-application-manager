package io.github.justinscottjenecke.job_application_manager.dto.jobApplication;

import io.github.justinscottjenecke.job_application_manager.model.Application;
import io.github.justinscottjenecke.job_application_manager.model.Job;

/**
 * A wrapper DTO containing an application entity as well as its associated job entity,
 * which is used to perform most sorting and filtering business logic on the client-side.
 *
 * @param job
 * @param application
 */
public record JobApplicationDto(
        Job job,
        Application application
        // job
//        Integer jobId,
//        String position,
//        String company,
//        String location,
//        String workModel,
//        Double postedSalary,
//        String coreSkill,
        // application
//        Integer applicationId,
//        Double costToCompany,
//        String applicationStatus,
//        String applicationStatusNotes,
//        Date dateApplied,
//        Date latestStatusUpdate,
//        Date dateFinalized
          ) {
}
