package io.github.justinscottjenecke.job_application_manager.repository;

import io.github.justinscottjenecke.job_application_manager.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJobRepository extends JpaRepository<Job, Integer> {
}
