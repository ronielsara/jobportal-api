package com.jobportal.jobportal_api.repository;

import com.jobportal.jobportal_api.model.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApplicationRepository extends JpaRepository<Application, Long>, JpaSpecificationExecutor<Application> {
    boolean existsByJobIdAndJobSeekerId(Long jobId, Long jobSeekerId);
}
