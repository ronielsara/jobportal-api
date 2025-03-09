package com.jobportal.jobportal_api.mapper;

import com.jobportal.jobportal_api.model.dto.request.JobCreateRequest;
import com.jobportal.jobportal_api.model.dto.response.JobResponse;
import com.jobportal.jobportal_api.model.entity.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public JobResponse toResponse(Job job) {
        return new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getLocation(),
                job.getSalary(),
                job.getEmployer().getName(),
                job.getCreatedAt()
        );
    }
    public Job toEntity(JobCreateRequest request) {
        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        return job;
    }
}


