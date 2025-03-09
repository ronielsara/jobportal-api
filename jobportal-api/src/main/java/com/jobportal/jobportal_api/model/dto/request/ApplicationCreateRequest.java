package com.jobportal.jobportal_api.model.dto.request;

import jakarta.validation.constraints.NotNull;

public class ApplicationCreateRequest {

    @NotNull(message = "Job ID is required")
    private Long jobId;

    @NotNull(message = "Job Seeker ID is required")
    private Long jobSeekerId;


    public Long getJobId() {
        return jobId;
    }

    public Long getJobSeekerId() {
        return jobSeekerId;
    }

}
