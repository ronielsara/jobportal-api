package com.jobportal.jobportal_api.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private User employer;

    public Review() {}

    public Review(String comment, int rating, Job job, User employer) {
        this.comment = comment;
        this.rating = rating;
        this.job = job;
        this.employer = employer;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }

    public User getEmployer() { return employer; }
    public void setEmployer(User employer) { this.employer = employer; }
}
