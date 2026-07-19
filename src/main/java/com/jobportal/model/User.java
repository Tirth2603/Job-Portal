package com.jobportal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    // JOBSEEKER or RECRUITER
    private String role;

    // Job seeker fields
    private String skills;
    private String resumeUrl;

    // Recruiter fields
    private String companyName;
    private String jobRole;
}
