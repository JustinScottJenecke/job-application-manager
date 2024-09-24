package io.github.justinscottjenecke.job_application_manager.dto.application;

import java.util.Date;

public record ApplicationDetailsDto(
        Integer jobId,
        Double costToCompany,
        String applicationStatus,
        String applicationStatusNotes,
        Date dateApplied,
        Date dateFinalized,
        String company) {
}
