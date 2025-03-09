package com.jobportal.jobportal_api.service;

import com.jobportal.jobportal_api.exception.UnauthorizedAccessException;
import com.jobportal.jobportal_api.model.entity.Application;
import com.jobportal.jobportal_api.model.entity.Job;
import com.jobportal.jobportal_api.model.entity.User;
import com.jobportal.jobportal_api.model.enums.ApplicationStatus;
import com.jobportal.jobportal_api.repository.ApplicationRepository;
import com.jobportal.jobportal_api.repository.JobRepository;
import com.jobportal.jobportal_api.repository.UserRepository;
import com.jobportal.jobportal_api.specification.ApplicationSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              JobRepository jobRepository,
                              UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public Page<Application> getApplicationsForEmployer(Long employerId, Long jobId, ApplicationStatus status, Pageable pageable) {
        Specification<Application> spec = Specification.where(ApplicationSpecification.jobPostedByEmployer(employerId));

        if (jobId != null) {
            spec = spec.and(ApplicationSpecification.hasJobId(jobId));
        }

        if (status != null) {
            spec = spec.and(ApplicationSpecification.hasStatus(status));
        }

        return applicationRepository.findAll(spec, pageable);
    }

    public Application updateApplicationStatus(Long applicationId, ApplicationStatus newStatus, Long employerId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("Application not found with id: " + applicationId));

        if (!application.getJob().getEmployer().getId().equals(employerId)) {
            throw new UnauthorizedAccessException("Only the employer who posted the job can update the status");
        }

        application.setStatus(newStatus);
        return applicationRepository.save(application);
    }

    public Application applyForJob(Long jobId, Long jobSeekerId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new EntityNotFoundException("Job not found with id: " + jobId));

        User jobSeeker = userRepository.findById(jobSeekerId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + jobSeekerId));

        boolean alreadyApplied = applicationRepository.existsByJobIdAndJobSeekerId(jobId, jobSeekerId);
        if (alreadyApplied) {
            throw new IllegalStateException("You have already applied to this job.");
        }

        Application application = new Application(job, jobSeeker);
        return applicationRepository.save(application);
    }

    public Page<Application> getApplicationsByJobSeeker(Long jobSeekerId, String title, ApplicationStatus status, Pageable pageable) {

        Specification<Application> spec = Specification.where(ApplicationSpecification.hasJobSeekerId(jobSeekerId));

        if (title != null && !title.isEmpty()) {
            spec = spec.and(ApplicationSpecification.hasJobTitle(title));
        }

        if (status != null) {
            spec = spec.and(ApplicationSpecification.hasStatus(status));
        }

        return applicationRepository.findAll(spec, pageable);
    }

    public Application saveApplication(Application application) {
        return applicationRepository.save(application);
    }

}
