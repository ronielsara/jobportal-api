package com.jobportal.jobportal_api.service;

import com.jobportal.jobportal_api.exception.UnauthorizedAccessException;
import com.jobportal.jobportal_api.model.entity.Job;
import com.jobportal.jobportal_api.model.entity.Review;
import com.jobportal.jobportal_api.model.entity.User;
import com.jobportal.jobportal_api.repository.JobRepository;
import com.jobportal.jobportal_api.repository.ReviewRepository;
import com.jobportal.jobportal_api.repository.UserRepository;
import com.jobportal.jobportal_api.specification.ReviewSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, JobRepository jobRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public Page<Review> getReviewsForJob(Long jobId, Integer rating, Pageable pageable) {
        Specification<Review> spec = Specification.where(null);

        if (jobId != null) {
            spec = spec.and(ReviewSpecification.hasJobId(jobId));
        }

        if (rating != null) {
            spec = spec.and(ReviewSpecification.hasRating(rating));
        }

        return reviewRepository.findAll(spec, pageable);
    }

    public Review addReviewForJob(Long jobId, String comment, int rating, Long employerId) {
        // Fetch the job
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new EntityNotFoundException("Job not found with id: " + jobId));

        // Fetch the employer
        User employer = userRepository.findById(employerId)
                .orElseThrow(() -> new EntityNotFoundException("Employer not found with id: " + employerId));

        // Check if the employer is the one who posted the job
        if (!job.getEmployer().getId().equals(employerId)) {
            throw new UnauthorizedAccessException("Only the employer who posted the job can add a review");
        }

        // Create a new review
        Review review = new Review(comment, rating, job, employer);

        // Save and return the review
        return reviewRepository.save(review);
    }
}
