package com.jobportal.jobportal_api.specification;

import com.jobportal.jobportal_api.model.entity.Application;
import com.jobportal.jobportal_api.model.enums.ApplicationStatus;
import org.springframework.data.jpa.domain.Specification;

public class ApplicationSpecification {

    public static Specification<Application> hasJobId(Long jobId) {
        return (root, query, criteriaBuilder) ->
                jobId == null ? null : criteriaBuilder.equal(root.get("job").get("id"), jobId);
    }

    public static Specification<Application> hasStatus(ApplicationStatus status) {
        return (root, query, criteriaBuilder) ->
                status == null ? null : criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Application> jobPostedByEmployer(Long employerId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("job").get("employer").get("id"), employerId);
    }

    public static Specification<Application> hasJobSeekerId(Long jobSeekerId) {
        return (root, query, criteriaBuilder) ->
                jobSeekerId == null ? null : criteriaBuilder.equal(root.get("jobSeeker").get("id"), jobSeekerId);
    }

    public static Specification<Application> hasJobTitle(String jobTitle) {
        return (root, query, criteriaBuilder) ->
                jobTitle == null || jobTitle.isEmpty() ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("job").get("title")), "%" + jobTitle.toLowerCase() + "%");
    }
}
