package com.jobportal.jobportal_api.repository;

import com.jobportal.jobportal_api.model.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
    // Custom queries will be handled by Specification
}
