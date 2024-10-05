package io.github.justinscottjenecke.job_application_manager.dto.jobApplication;

import io.github.justinscottjenecke.job_application_manager.model.Application;
import io.github.justinscottjenecke.job_application_manager.model.Job;

public record JobApplicationWrapper(Job job, Application application) {
}
