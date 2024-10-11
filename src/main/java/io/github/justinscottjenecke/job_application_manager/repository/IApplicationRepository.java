package io.github.justinscottjenecke.job_application_manager.repository;

import io.github.justinscottjenecke.job_application_manager.model.Application;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IApplicationRepository extends JpaRepository<Application, Integer> {

}
