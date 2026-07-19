package com.jobportal.controller;

import com.jobportal.model.Job;
import com.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    // Get all active jobs
    @GetMapping
    public ResponseEntity<?> getAllJobs() {
        return ResponseEntity.ok(jobRepository.findByStatus("ACTIVE"));
    }

    // Get job by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getJob(@PathVariable Long id) {
        return jobRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // Search jobs by keyword or location
    @GetMapping("/search")
    public ResponseEntity<?> searchJobs(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String location) {

        if (keyword != null && !keyword.isEmpty()) {
            List<Job> results = jobRepository
                .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrLocationContainingIgnoreCase(
                    keyword, keyword, keyword);
            return ResponseEntity.ok(results);
        }
        if (location != null && !location.isEmpty()) {
            return ResponseEntity.ok(jobRepository.findByLocationContainingIgnoreCaseAndStatus(location, "ACTIVE"));
        }
        return ResponseEntity.ok(jobRepository.findByStatus("ACTIVE"));
    }

    // Post a job (Recruiter)
    @PostMapping
    public ResponseEntity<?> createJob(@RequestBody Job job) {
        job.setStatus("ACTIVE");
        return ResponseEntity.ok(jobRepository.save(job));
    }

    // Edit job (Recruiter)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@PathVariable Long id, @RequestBody Job updated) {
        return jobRepository.findById(id).map(job -> {
            job.setTitle(updated.getTitle());
            job.setDescription(updated.getDescription());
            job.setLocation(updated.getLocation());
            job.setSalary(updated.getSalary());
            job.setCompany(updated.getCompany());
            job.setStatus(updated.getStatus());
            return ResponseEntity.ok(jobRepository.save(job));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete job (Recruiter)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id) {
        jobRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Job deleted"));
    }

    // Get jobs by recruiter
    @GetMapping("/recruiter/{recruiterId}")
    public ResponseEntity<?> getJobsByRecruiter(@PathVariable Long recruiterId) {
        return ResponseEntity.ok(jobRepository.findByRecruiterId(recruiterId));
    }
}
