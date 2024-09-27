package io.github.justinscottjenecke.job_application_manager.dto.application;

import java.util.Date;

/**
 * DTO class for creating Application instances in the use case of a user applying for a
 * Job already existing in the system
 *
 * @param jobId References the existing job within the system
 * @param costToCompany
 * @param applicationStatusNotes Any additional notes the user wants to add to their application
 */
public record CreateApplicationDto(
        Integer jobId,
        Double costToCompany,
        String applicationStatusNotes) {
}
