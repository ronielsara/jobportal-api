package com.jobportal.jobportal_api.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class JobCreateRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 10, message = "Description must be at least 10 characters long")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be a positive number")
    private double salary;

    public JobCreateRequest() {}

    public JobCreateRequest(String title, String description, String location, double salary) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.salary = salary;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public double getSalary() { return salary; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setLocation(String location) { this.location = location; }
    public void setSalary(double salary) { this.salary = salary; }
}
