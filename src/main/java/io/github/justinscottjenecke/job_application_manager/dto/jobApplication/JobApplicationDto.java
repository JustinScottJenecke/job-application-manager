package io.github.justinscottjenecke.job_application_manager.dto.jobApplication;

/**
 * DTO for returning a summary of a job application with enough relevant information.
 * Information provided by dto is to be used a preview, in a button, or in a list.
 *
 * @param applicationId id field of associated application model entity
 * @param jobId id field of associated job model entity
 * @param company
 * @param position
 * @param location
 * @param applicationStatus
 */
public record JobApplicationDto(
        Integer applicationId,
        Integer jobId,
        String company,
        String position,
        String location,
        String applicationStatus) {

}
