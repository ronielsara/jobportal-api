package com.jobportal.jobportal_api.specification;

import com.jobportal.jobportal_api.model.entity.Job;
import org.springframework.data.jpa.domain.Specification;

public class JobSpecification {

    public static Specification<Job> hasEmployerId(Long employerId) {
        return (root, query, cb) ->
                employerId == null ? null : cb.equal(root.get("employer").get("id"), employerId);
    }

    public static Specification<Job> titleContains(String title) {
        return (root, query, cb) ->
                title == null ? null : cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Job> locatedIn(String location) {
        return (root, query, cb) ->
                location == null ? null : cb.like(cb.lower(root.get("location")), "%" + location.toLowerCase() + "%");
    }
}

