# 🏢 Job Portal - Spring Boot REST API

A simple, clean Job Portal backend built with Spring Boot + PostgreSQL.
Perfect as a personal/resume project.

---

## 🛠 Tech Stack
- Java 17
- Spring Boot 3.2
- Spring Data JPA
- PostgreSQL
- Lombok

---

## ⚙️ Setup Instructions

### 1. PostgreSQL Setup
```sql
-- Run in psql as superuser:
CREATE USER admin WITH PASSWORD 'admin';
CREATE DATABASE jobportal OWNER admin;
GRANT ALL PRIVILEGES ON DATABASE jobportal TO admin;
```
Then run `db_setup.sql` to create tables and insert sample data.

### 2. Run the App
```bash
mvn spring-boot:run
```
App runs at: **http://localhost:8081**

> Spring Boot will auto-create tables via `ddl-auto=update`.

---

## 📁 Project Structure
```
jobportal/
├── src/main/java/com/jobportal/
│   ├── JobPortalApplication.java
│   ├── model/
│   │   ├── User.java
│   │   ├── Job.java
│   │   ├── Application.java
│   │   └── SavedJob.java
│   ├── repository/
│   │   ├── UserRepository.java
│   │   ├── JobRepository.java
│   │   ├── ApplicationRepository.java
│   │   └── SavedJobRepository.java
│   └── controller/
│       ├── UserController.java
│       ├── JobController.java
│       ├── ApplicationController.java
│       └── SavedJobController.java
├── src/main/resources/
│   └── application.properties
├── db_setup.sql
├── JobPortal_API_Collection.json  ← Import into Postman
└── pom.xml
```

---

## 🔗 API Endpoints

### Users `/api/users`
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/register` | Register new user |
| POST | `/login` | Login |
| GET | `/{id}` | Get user profile |
| PUT | `/{id}` | Update profile |
| GET | `/` | All users |

### Jobs `/api/jobs`
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | All active jobs |
| GET | `/{id}` | Job by ID |
| GET | `/search?keyword=java` | Search by keyword |
| GET | `/search?location=bangalore` | Filter by location |
| POST | `/` | Post new job (recruiter) |
| PUT | `/{id}` | Update job |
| DELETE | `/{id}` | Delete job |
| GET | `/recruiter/{id}` | Jobs by recruiter |

### Applications `/api/applications`
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/apply` | Apply for job |
| GET | `/user/{userId}` | My applications |
| GET | `/job/{jobId}` | Applicants for job |
| PUT | `/{id}/status` | Update status (SHORTLISTED/REJECTED) |

### Saved Jobs `/api/saved`
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/` | Bookmark a job |
| GET | `/user/{userId}` | My saved jobs |
| DELETE | `/{id}` | Remove bookmark |

---

## 👤 User Roles
- **JOBSEEKER** – Can browse, apply, and save jobs
- **RECRUITER** – Can post, edit, delete jobs and view applicants

---

## 📬 API Testing
Import `JobPortal_API_Collection.json` into **Postman** and test all endpoints.

---

## 💡 Features
- ✅ User Registration & Login
- ✅ Job Seeker & Recruiter profiles
- ✅ Post / Edit / Delete Jobs
- ✅ Job Listing & Search (keyword + location)
- ✅ One-click Apply
- ✅ View Applicants (Recruiter)
- ✅ Application Status (Applied / Shortlisted / Rejected)
- ✅ Save / Bookmark Jobs
