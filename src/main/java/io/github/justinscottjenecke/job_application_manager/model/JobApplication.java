package io.github.justinscottjenecke.job_application_manager.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class JobApplication {
    private Integer id;
    private Integer jobId;
    private Integer applicationId;
}
