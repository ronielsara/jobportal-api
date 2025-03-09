package com.jobportal.jobportal_api.model.dto.request;

import com.jobportal.jobportal_api.model.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

public class ApplicationStatusUpdateRequest {

    @NotNull(message = "Status is required")
    private ApplicationStatus status;

    @NotNull(message = "Employer ID is required")
    private Long employerId;

    public ApplicationStatus getStatus() {
        return status;
    }
    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
    public Long getEmployerId() {
        return employerId;
    }
    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }
}
