package io.github.justinscottjenecke.job_application_manager.dto.application;

import java.util.Date;

/**
 * DTO used to display the granular details of an application or to make any edits, updates, or
 * delete an application.
 *
 * @param jobId
 * @param costToCompany
 * @param applicationStatus
 * @param applicationStatusNotes
 * @param dateApplied
 * @param latestStatusUpdate Timestamp created when an application's status is changed
 * @param dateFinalized
 * @param company
 */
public record ApplicationDetailsDto(
        Integer jobId,
        Double costToCompany,
        String applicationStatus,
        String applicationStatusNotes,
        Date dateApplied,
        Date latestStatusUpdate,
        Date dateFinalized,
        String company) {
}
