package com.jobportal.controller;

import com.jobportal.model.SavedJob;
import com.jobportal.repository.SavedJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/saved")
@CrossOrigin(origins = "*")
public class SavedJobController {

    @Autowired
    private SavedJobRepository savedJobRepository;

    // Save a job
    @PostMapping
    public ResponseEntity<?> saveJob(@RequestBody Map<String, Long> body) {
        Long userId = body.get("userId");
        Long jobId = body.get("jobId");

        if (savedJobRepository.existsByUserIdAndJobId(userId, jobId)) {
            return ResponseEntity.badRequest().body(Map.of("message", "Job already saved"));
        }

        SavedJob savedJob = new SavedJob();
        savedJob.setUserId(userId);
        savedJob.setJobId(jobId);

        return ResponseEntity.ok(savedJobRepository.save(savedJob));
    }

    // Get saved jobs for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getSavedJobs(@PathVariable Long userId) {
        return ResponseEntity.ok(savedJobRepository.findByUserId(userId));
    }

    // Remove saved job
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeSavedJob(@PathVariable Long id) {
        savedJobRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Removed from saved jobs"));
    }
}
