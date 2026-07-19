package com.jobportal.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Data
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;
    private Long applicantId;

    // APPLIED, SHORTLISTED, REJECTED
    private String status = "APPLIED";

    private LocalDateTime appliedAt = LocalDateTime.now();
}
