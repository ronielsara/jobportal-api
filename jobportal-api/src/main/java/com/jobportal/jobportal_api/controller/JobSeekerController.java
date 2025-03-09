package com.jobportal.jobportal_api.controller;

import com.jobportal.jobportal_api.mapper.ApplicationMapper;
import com.jobportal.jobportal_api.mapper.JobMapper;
import com.jobportal.jobportal_api.model.dto.request.ApplicationCreateRequest;
import com.jobportal.jobportal_api.model.dto.response.ApplicationResponse;
import com.jobportal.jobportal_api.model.dto.response.JobResponse;
import com.jobportal.jobportal_api.model.entity.Application;
import com.jobportal.jobportal_api.model.entity.Job;
import com.jobportal.jobportal_api.model.entity.User;
import com.jobportal.jobportal_api.model.enums.ApplicationStatus;
import com.jobportal.jobportal_api.service.ApplicationService;
import com.jobportal.jobportal_api.service.JobService;
import com.jobportal.jobportal_api.service.ResumeService;
import com.jobportal.jobportal_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/jobseeker")
public class JobSeekerController {

    private final ResumeService resumeService;
    private final ApplicationService applicationService;
    private final ApplicationMapper applicationMapper;
    private final JobService jobService;
    private final JobMapper jobMapper;
    private final UserService userService;

    public JobSeekerController(ResumeService resumeService, ApplicationService applicationService, ApplicationMapper applicationMapper, JobService jobService, JobMapper jobMapper,UserService userService) {
        this.resumeService = resumeService;
        this.applicationService = applicationService;
        this.applicationMapper = applicationMapper;
        this.jobService = jobService;
        this.jobMapper = jobMapper;
        this.userService = userService;
    }

    @PostMapping("/upload-resume")
    public ResponseEntity<String> uploadResume(
            @RequestParam Long jobSeekerId,
            @RequestParam("file") MultipartFile file) {

        try {
            String fileName = resumeService.uploadResume(jobSeekerId, file);
            return ResponseEntity.ok("Resume uploaded successfully: " + fileName);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload resume: " + e.getMessage());
        }
    }

    @PostMapping("/apply")
    public ResponseEntity<ApplicationResponse> applyForJob(
            @Valid @RequestBody ApplicationCreateRequest request) {
        Job job = jobService.getJobById(request.getJobId());
        User jobSeeker = userService.getUserById(request.getJobSeekerId());
        Application application = applicationMapper.toEntity(request, job, jobSeeker);
        Application savedApplication = applicationService.saveApplication(application);
        return ResponseEntity.status(201).body(applicationMapper.toResponse(savedApplication));
    }


    @GetMapping("/applications")
    public ResponseEntity<Page<ApplicationResponse>> getApplicationsByJobSeeker(
            @RequestParam Long jobSeekerId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) ApplicationStatus status,
            Pageable pageable) {

        Page<Application> applications = applicationService.getApplicationsByJobSeeker(jobSeekerId, title, status, pageable);

        return ResponseEntity.ok(applications.map(applicationMapper::toResponse));
    }

    @GetMapping("/jobs")
    public ResponseEntity<Page<JobResponse>> getAllJobs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Long employerId,
            Pageable pageable) {

        Page<Job> jobsPage = jobService.getAllJobs(title, location, employerId, pageable);

        return ResponseEntity.ok(jobsPage.map(jobMapper::toResponse));
    }
}
