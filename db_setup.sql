-- ============================================
-- Job Portal Database Setup Script
-- PostgreSQL | DB: jobportal
-- User: admin | Password: admin
-- ============================================

-- Step 1: Create user and database (run as superuser/postgres)
-- CREATE USER admin WITH PASSWORD 'admin';
-- CREATE DATABASE jobportal OWNER admin;
-- GRANT ALL PRIVILEGES ON DATABASE jobportal TO admin;

-- Step 2: Connect to jobportal DB and run below

-- NOTE: Spring Boot (ddl-auto=update) will auto-create tables.
-- This script is for manual setup / reference.

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(100),
    role VARCHAR(20),           -- JOBSEEKER or RECRUITER
    skills TEXT,
    resume_url VARCHAR(300),
    company_name VARCHAR(150),
    job_role VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS jobs (
    id SERIAL PRIMARY KEY,
    title VARCHAR(200),
    description TEXT,
    location VARCHAR(100),
    salary VARCHAR(100),
    company VARCHAR(150),
    recruiter_id BIGINT,
    posted_at TIMESTAMP DEFAULT NOW(),
    status VARCHAR(20) DEFAULT 'ACTIVE'
);

CREATE TABLE IF NOT EXISTS applications (
    id SERIAL PRIMARY KEY,
    job_id BIGINT,
    applicant_id BIGINT,
    status VARCHAR(20) DEFAULT 'APPLIED',   -- APPLIED, SHORTLISTED, REJECTED
    applied_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS saved_jobs (
    id SERIAL PRIMARY KEY,
    user_id BIGINT,
    job_id BIGINT
);

-- ============================================
-- Sample Data
-- ============================================

-- Recruiter
INSERT INTO users (name, email, password, role, company_name, job_role)
VALUES ('Alice Recruiter', 'alice@techcorp.com', 'pass123', 'RECRUITER', 'TechCorp', 'HR Manager');

-- Job Seekers
INSERT INTO users (name, email, password, role, skills, resume_url)
VALUES ('Bob Seeker', 'bob@gmail.com', 'pass123', 'JOBSEEKER', 'Java, Spring Boot, SQL', 'https://resume.io/bob');

INSERT INTO users (name, email, password, role, skills, resume_url)
VALUES ('Carol Dev', 'carol@gmail.com', 'pass123', 'JOBSEEKER', 'Python, Django, React', 'https://resume.io/carol');

-- Jobs (recruiterId = 1 = Alice)
INSERT INTO jobs (title, description, location, salary, company, recruiter_id)
VALUES ('Java Backend Developer', 'We need a Spring Boot developer with 2+ years experience.', 'Bangalore', '8-12 LPA', 'TechCorp', 1);

INSERT INTO jobs (title, description, location, salary, company, recruiter_id)
VALUES ('Python Developer', 'Looking for Django/FastAPI developer.', 'Remote', '10-15 LPA', 'TechCorp', 1);

INSERT INTO jobs (title, description, location, salary, company, recruiter_id)
VALUES ('Full Stack Developer', 'React + Node.js developer needed.', 'Hyderabad', '12-18 LPA', 'TechCorp', 1);

-- Applications
INSERT INTO applications (job_id, applicant_id, status) VALUES (1, 2, 'APPLIED');
INSERT INTO applications (job_id, applicant_id, status) VALUES (2, 3, 'APPLIED');

-- Saved Jobs
INSERT INTO saved_jobs (user_id, job_id) VALUES (2, 2);
INSERT INTO saved_jobs (user_id, job_id) VALUES (2, 3);
