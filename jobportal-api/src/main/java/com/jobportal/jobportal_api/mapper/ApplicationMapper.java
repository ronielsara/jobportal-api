package com.jobportal.jobportal_api.mapper;


import com.jobportal.jobportal_api.model.dto.request.ApplicationCreateRequest;
import com.jobportal.jobportal_api.model.dto.response.ApplicationResponse;
import com.jobportal.jobportal_api.model.entity.Application;
import com.jobportal.jobportal_api.model.entity.Job;
import com.jobportal.jobportal_api.model.entity.User;
import com.jobportal.jobportal_api.model.enums.ApplicationStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class ApplicationMapper {
    public ApplicationResponse toResponse(Application application) {
        return new ApplicationResponse(
                application.getId(),
                application.getJob().getTitle(),
                application.getJobSeeker().getName(),
                application.getStatus(),
                application.getAppliedAt()
        );
    }

    public Application toEntity(ApplicationCreateRequest request, Job job, User jobSeeker) {
        Application application = new Application();
        application.setJob(job);
        application.setJobSeeker(jobSeeker);
        application.setStatus(ApplicationStatus.PENDING);
        application.setAppliedAt(LocalDateTime.now());
        return application;
    }
}

