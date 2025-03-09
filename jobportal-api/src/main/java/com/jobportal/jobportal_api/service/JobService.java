package com.jobportal.jobportal_api.service;

import com.jobportal.jobportal_api.model.entity.Application;
import com.jobportal.jobportal_api.model.entity.Job;
import com.jobportal.jobportal_api.model.entity.User;
import com.jobportal.jobportal_api.repository.ApplicationRepository;
import com.jobportal.jobportal_api.repository.JobRepository;
import com.jobportal.jobportal_api.repository.UserRepository;
import com.jobportal.jobportal_api.specification.JobSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    public JobService(JobRepository jobRepository , UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;

    }

    public Page<Job> getJobsByEmployer(Long employerId, String title, String location, Pageable pageable) {
        Specification<Job> spec = Specification.where(JobSpecification.hasEmployerId(employerId));

        if (title != null && !title.isEmpty()) {
            spec = spec.and(JobSpecification.titleContains(title));
        }

        if (location != null && !location.isEmpty()) {
            spec = spec.and(JobSpecification.locatedIn(location));
        }

        return jobRepository.findAll(spec, pageable);
    }

    public Job postJob(Job job, Long employerId) {
        User employer = userRepository.findById(employerId)
                .orElseThrow(() -> new EntityNotFoundException("Employer not found with id: " + employerId));

        job.setEmployer(employer);
        return jobRepository.save(job);
    }


    public Page<Job> getAllJobs(String title, String location, Long employerId, Pageable pageable) {
        Specification<Job> spec = Specification.where(null);

        if (title != null && !title.isEmpty()) {
            spec = spec.and(JobSpecification.titleContains(title));
        }

        if (location != null && !location.isEmpty()) {
            spec = spec.and(JobSpecification.locatedIn(location));
        }

        if (employerId != null) {
            spec = spec.and(JobSpecification.hasEmployerId(employerId));
        }

        return jobRepository.findAll(spec, pageable);
    }

}
