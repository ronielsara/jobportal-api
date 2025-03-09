package com.jobportal.jobportal_api.repository;

import com.jobportal.jobportal_api.model.entity.Review;
import com.jobportal.jobportal_api.model.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {
    Page<Review> findByJob(Job job, Pageable pageable);
}
