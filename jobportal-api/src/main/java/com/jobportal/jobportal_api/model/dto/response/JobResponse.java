package com.jobportal.jobportal_api.model.dto.response;

import java.time.LocalDateTime;

public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private String location;
    private double salary;
    private String employerName;
    private LocalDateTime createdAt;

    public JobResponse(Long id, String title, String description, String location, double salary, String employerName, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.salary = salary;
        this.employerName = employerName;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public double getSalary() { return salary; }
    public String getEmployerName() { return employerName; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
