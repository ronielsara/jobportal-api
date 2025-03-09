package com.jobportal.jobportal_api.controller;

import com.jobportal.jobportal_api.mapper.ApplicationMapper;
import com.jobportal.jobportal_api.mapper.ReviewMapper;
import com.jobportal.jobportal_api.mapper.UserMapper;
import com.jobportal.jobportal_api.model.dto.request.ApplicationStatusUpdateRequest;
import com.jobportal.jobportal_api.model.dto.request.JobCreateRequest;
import com.jobportal.jobportal_api.model.dto.request.ReviewCreateRequest;
import com.jobportal.jobportal_api.model.dto.response.JobResponse;
import com.jobportal.jobportal_api.model.dto.response.ApplicationResponse;
import com.jobportal.jobportal_api.model.dto.response.ReviewResponse;
import com.jobportal.jobportal_api.model.entity.Job;
import com.jobportal.jobportal_api.model.entity.Application;
import com.jobportal.jobportal_api.model.entity.Review;
import com.jobportal.jobportal_api.model.entity.User;
import com.jobportal.jobportal_api.model.enums.ApplicationStatus;
import com.jobportal.jobportal_api.service.JobService;
import com.jobportal.jobportal_api.service.ApplicationService;
import com.jobportal.jobportal_api.service.ReviewService;
import com.jobportal.jobportal_api.mapper.JobMapper;
import com.jobportal.jobportal_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employer")
public class EmployerController {

    private final JobService jobService;
    private final ApplicationService applicationService;
    private final ReviewService reviewService;
    private final JobMapper jobMapper; // Inject JobMapper
    private final ApplicationMapper applicationMapper;
    private final ReviewMapper reviewMapper;
    private final UserService userService;

    public EmployerController(JobService jobService, ApplicationService applicationService, ReviewService reviewService, JobMapper jobMapper, ApplicationMapper applicationMapper, ReviewMapper reviewMapper,UserService userservice) {
        this.jobService = jobService;
        this.applicationService = applicationService;
        this.reviewService = reviewService;
        this.jobMapper = jobMapper;
        this.applicationMapper = applicationMapper;
        this.reviewMapper = reviewMapper;
        this.userService = userservice;
    }

    @PostMapping("/post-job")
    public ResponseEntity<JobResponse> postJob(
            @Valid @RequestBody JobCreateRequest jobCreateRequest,
            @RequestParam Long employerId) {
        User employer = userService.getUserById(employerId);
        Job job = jobMapper.toEntity(jobCreateRequest, employer);
        Job postedJob = jobService.saveJob(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobMapper.toResponse(postedJob));
    }


    @GetMapping("/jobs")
    public ResponseEntity<Page<JobResponse>> getJobsByEmployer(
            @RequestParam Long employerId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            Pageable pageable) {

        Page<Job> jobsPage = jobService.getJobsByEmployer(employerId, title, location, pageable);
        return ResponseEntity.ok(jobsPage.map(jobMapper::toResponse));
    }

    @GetMapping("/applications/{jobId}")
    public ResponseEntity<Page<ApplicationResponse>> getApplicationsForJob(
            @PathVariable Long jobId,
            @RequestParam Long employerId,
            @RequestParam(required = false) String status,
            Pageable pageable) {

        ApplicationStatus applicationStatus = (status != null) ? ApplicationStatus.valueOf(status.toUpperCase()) : null;

        Page<Application> applications = applicationService.getApplicationsForEmployer(employerId, jobId, applicationStatus, pageable);

        return ResponseEntity.ok(applications.map(applicationMapper::toResponse));
    }

    @PutMapping("/applications/{applicationId}/status")
    public ResponseEntity<ApplicationResponse> updateApplicationStatus(
            @PathVariable Long applicationId,
            @Valid @RequestBody ApplicationStatusUpdateRequest request) {

        Application application = applicationService.updateApplicationStatus(
                applicationId,
                request.getStatus(),
                request.getEmployerId()
        );
        return ResponseEntity.ok(applicationMapper.toResponse(application));
    }

    @PostMapping("/reviews/{jobId}")
    public ResponseEntity<ReviewResponse> addReviewForJob(
            @PathVariable Long jobId,
            @Valid @RequestBody ReviewCreateRequest reviewRequest,
            @RequestParam Long employerId) {

        if (!jobId.equals(reviewRequest.getJobId())) {
            throw new IllegalArgumentException("Job ID in the path and in the request body must match.");
        }

        Review review = reviewService.addReviewForJob(
                jobId,
                reviewRequest.getComment(),
                reviewRequest.getRating(),
                employerId);

        return ResponseEntity.status(HttpStatus.CREATED).body(reviewMapper.toResponse(review));
    }


}
