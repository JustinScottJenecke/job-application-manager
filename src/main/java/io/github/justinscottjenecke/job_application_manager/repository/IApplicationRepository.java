package io.github.justinscottjenecke.job_application_manager.repository;

import io.github.justinscottjenecke.job_application_manager.model.Application;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IApplicationRepository extends JpaRepository<Application, Integer> {

    EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("job_app_jpa");
    EntityManager entityManager = emFactory.createEntityManager();

    Query readAll = entityManager.createQuery(
            "Select "
    );
}
