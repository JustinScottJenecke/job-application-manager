package io.github.justinscottjenecke.job_application_manager.repository;

import io.github.justinscottjenecke.job_application_manager.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobRepository extends JpaRepository<Job, Integer> {
}
