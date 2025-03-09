package com.jobportal.jobportal_api.model.dto.response;

public class ReviewResponse {
    private Long id;
    private String comment;
    private int rating;
    private String employerName;
    private String jobTitle;

    public ReviewResponse(Long id, String comment, int rating, String employerName, String jobTitle) {
        this.id = id;
        this.comment = comment;
        this.rating = rating;
        this.employerName = employerName;
        this.jobTitle = jobTitle;
    }

    public Long getId() { return id; }
    public String getComment() { return comment; }
    public int getRating() { return rating; }
    public String getEmployerName() { return employerName; }
    public String getJobTitle() { return jobTitle; }
}

