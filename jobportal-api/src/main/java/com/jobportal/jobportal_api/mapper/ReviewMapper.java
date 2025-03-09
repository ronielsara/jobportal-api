package com.jobportal.jobportal_api.mapper;

import com.jobportal.jobportal_api.model.dto.request.ReviewCreateRequest;
import com.jobportal.jobportal_api.model.dto.response.ReviewResponse;
import com.jobportal.jobportal_api.model.entity.Review;
import com.jobportal.jobportal_api.model.entity.Job;
import com.jobportal.jobportal_api.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewResponse toResponse(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getComment(),
                review.getRating(),
                review.getEmployer().getName(),
                review.getJob().getTitle()
        );
    }
}
