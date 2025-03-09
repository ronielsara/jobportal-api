package com.jobportal.jobportal_api.model.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReviewCreateRequest {

    @NotNull(message = "Job ID is required")
    private Long jobId;

    @NotBlank(message = "Comment is required")
    @Size(min = 1, max = 500, message = "Comment must be between 1 and 500 characters")
    private String comment;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private int rating;



    public ReviewCreateRequest(Long jobId, String comment, int rating) {
        this.jobId = jobId;
        this.comment = comment;
        this.rating = rating;
    }

    public Long getJobId() { return jobId; }
    public String getComment() { return comment; }
    public int getRating() { return rating; }

}
