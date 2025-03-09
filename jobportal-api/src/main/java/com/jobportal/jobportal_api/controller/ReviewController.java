package com.jobportal.jobportal_api.controller;

import com.jobportal.jobportal_api.model.dto.response.ReviewResponse;
import com.jobportal.jobportal_api.model.entity.Review;
import com.jobportal.jobportal_api.service.ReviewService;
import com.jobportal.jobportal_api.mapper.ReviewMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    public ReviewController(ReviewService reviewService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }

    @GetMapping("/reviews/{jobId}")
    public ResponseEntity<Page<ReviewResponse>> getReviewsForJob(
            @PathVariable Long jobId,
            @RequestParam(required = false) Integer rating,
            Pageable pageable) {

        Page<Review> reviewsPage = reviewService.getReviewsForJob(jobId, rating, pageable);

        return ResponseEntity.ok(reviewsPage.map(reviewMapper::toResponse));
    }
}
