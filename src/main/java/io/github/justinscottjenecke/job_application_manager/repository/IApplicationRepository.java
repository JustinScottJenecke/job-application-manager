package io.github.justinscottjenecke.job_application_manager.repository;

import io.github.justinscottjenecke.job_application_manager.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IApplicationRepository extends JpaRepository<Application, Integer> {
}
