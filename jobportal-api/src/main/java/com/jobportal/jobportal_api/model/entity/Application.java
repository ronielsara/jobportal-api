package com.jobportal.jobportal_api.model.entity;

import com.jobportal.jobportal_api.model.enums.ApplicationStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private User jobSeeker;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalDateTime appliedAt;

    public Application() {
        this.appliedAt = LocalDateTime.now();
        this.status = ApplicationStatus.PENDING;
    }

    public Application(Job job, User jobSeeker) {
        this.job = job;
        this.jobSeeker = jobSeeker;
        this.appliedAt = LocalDateTime.now();
        this.status = ApplicationStatus.PENDING;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }

    public User getJobSeeker() { return jobSeeker; }
    public void setJobSeeker(User jobSeeker) { this.jobSeeker = jobSeeker; }

    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }

    public LocalDateTime getAppliedAt() { return appliedAt; }
}
