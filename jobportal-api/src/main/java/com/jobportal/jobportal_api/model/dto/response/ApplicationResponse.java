package com.jobportal.jobportal_api.model.dto.response;
import com.jobportal.jobportal_api.model.enums.ApplicationStatus;

import java.time.LocalDateTime;

public class ApplicationResponse {
    private Long id;
    private String jobTitle;
    private String jobSeekerName;
    private ApplicationStatus status;
    private LocalDateTime appliedAt;

    public ApplicationResponse(Long id, String jobTitle, String jobSeekerName, ApplicationStatus status, LocalDateTime appliedAt) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.jobSeekerName = jobSeekerName;
        this.status = status;
        this.appliedAt = appliedAt;
    }

    public Long getId() { return id; }
    public String getJobTitle() { return jobTitle; }
    public String getJobSeekerName() { return jobSeekerName; }
    public ApplicationStatus getStatus() { return status; }
    public LocalDateTime getAppliedAt() { return appliedAt; }
}

