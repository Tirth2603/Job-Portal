package com.jobportal.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private String salary;
    private String company;

    // Recruiter who posted this job
    private Long recruiterId;

    private LocalDateTime postedAt = LocalDateTime.now();

    // Optional: active / closed
    private String status = "ACTIVE";
}
