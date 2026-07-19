package com.jobportal.controller;

import com.jobportal.model.Application;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    // Apply for a job (one-click)
    @PostMapping("/apply")
    public ResponseEntity<?> applyForJob(@RequestBody Map<String, Long> body) {
        Long jobId = body.get("jobId");
        Long applicantId = body.get("applicantId");

        if (applicationRepository.existsByJobIdAndApplicantId(jobId, applicantId)) {
            return ResponseEntity.badRequest().body(Map.of("message", "Already applied for this job"));
        }

        Application app = new Application();
        app.setJobId(jobId);
        app.setApplicantId(applicantId);
        app.setStatus("APPLIED");

        return ResponseEntity.ok(applicationRepository.save(app));
    }

    // Get applications by job seeker
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getApplicationsByUser(@PathVariable Long userId) {
        List<Application> apps = applicationRepository.findByApplicantId(userId);
        return ResponseEntity.ok(apps);
    }

    // Get applicants for a job (Recruiter view)
    @GetMapping("/job/{jobId}")
    public ResponseEntity<?> getApplicationsByJob(@PathVariable Long jobId) {
        List<Application> apps = applicationRepository.findByJobId(jobId);
        return ResponseEntity.ok(apps);
    }

    // Update application status (Recruiter: SHORTLISTED / REJECTED)
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return applicationRepository.findById(id).map(app -> {
            app.setStatus(body.get("status"));
            return ResponseEntity.ok(applicationRepository.save(app));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Get all applications (admin/debug)
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(applicationRepository.findAll());
    }
}
