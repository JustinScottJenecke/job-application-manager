package io.github.justinscottjenecke.job_application_manager.service;

import io.github.justinscottjenecke.job_application_manager.service.mappers.ApplicationMapper;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    public final ApplicationMapper mapper;

    public ApplicationService(ApplicationMapper applicationMapper) {
        this.mapper = applicationMapper;
    }
}
