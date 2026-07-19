package com.jobportal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "saved_jobs")
@Data
public class SavedJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long jobId;
}
