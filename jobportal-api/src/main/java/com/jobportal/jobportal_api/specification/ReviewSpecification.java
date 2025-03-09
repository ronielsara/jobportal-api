package com.jobportal.jobportal_api.specification;

import com.jobportal.jobportal_api.model.entity.Review;
import org.springframework.data.jpa.domain.Specification;

public class ReviewSpecification {

    public static Specification<Review> hasJobId(Long jobId) {
        return (root, query, criteriaBuilder) ->
                jobId == null ? null : criteriaBuilder.equal(root.get("job").get("id"), jobId);
    }

    public static Specification<Review> hasRating(Integer rating) {
        return (root, query, criteriaBuilder) ->
                rating == null ? null : criteriaBuilder.equal(root.get("rating"), rating);
    }
}
