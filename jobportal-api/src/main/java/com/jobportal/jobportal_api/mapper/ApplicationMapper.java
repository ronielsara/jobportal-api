package com.jobportal.jobportal_api.mapper;

import com.jobportal.jobportal_api.model.dto.response.ApplicationResponse;
import com.jobportal.jobportal_api.model.entity.Application;
import org.springframework.stereotype.Component;
@Component
public class ApplicationMapper {
    public  ApplicationResponse toResponse(Application application) {
        return new ApplicationResponse(
                application.getId(),
                application.getJob().getTitle(),
                application.getJobSeeker().getName(),
                application.getStatus(),
                application.getAppliedAt()
        );
    }
}
