# 🚗 Auto School Website System

## 📘 1. Introduction

### 1.1 Purpose

This document defines the functional and non-functional requirements for the Auto School Website System. The system allows:

- Students to register for driving exams, receive notifications, view results, and manage their licenses.
- Instructors to manage exam availability and student evaluations.

### 1.2 Scope

The Auto School Website enables:

- Student registration for exams
- Automated reminders and notifications
- Result recording and feedback from instructors
- Alerts for theoretical exam expiry
- Limits on exam number per day

The platform supports **students**, **instructors**, and **administrators** with different access levels.

---

## 🌐 2. Overall Description

### 2.1 Product Perspective

A web-based platform that digitizes the driving exam process in an auto school. It provides exam scheduling, user tracking, and management features.

### 2.2 User Classes and Characteristics

- **Student**: Registers for exams, views results, receives notifications.
- **Instructor**: Sets exam availability, submits exam results.
- **Admin**: Manages users, instructors, and overall system configuration.

### 2.3 Assumptions and Dependencies

- A reliable email/SMS provider must be available for notifications.
- The database engine must support foreign keys and transactions (PostgreSQL is used).

---

## ✅ 3. Functional Requirements

### FR1: Practical Exam Registration

- Students log in and register for exams.
- The system displays available dates, slots, and instructors.

### FR2: Exam Notification

- Students are notified **48 hours before** their scheduled practical exam via email/SMS/in-app.

### FR3: Retake Notification

- Failed students receive:
    - Notification of retake eligibility
    - A list of available retake slots

### FR4: Exam Result Management

- Instructors record pass/fail status, score, and feedback.
- Students can view results in their profile.

### FR5: Theoretical Exam Expiry Alert

- System tracks theoretical exam expiry.
- Notifications sent **30, 7, and 1 day** before expiration.

### FR6: Daily Registration Limit

- Each exam day has a `max_students` limit.
- System prevents overbooking by comparing with `current_students`.

---

## 🔒 4. Non-Functional Requirements

### NFR1: Security

- Secure password hashing
- 2FA (Two-Factor Authentication) support
- Role-based access control

### NFR2: Availability

- **99.9% uptime**, excluding maintenance.

### NFR3: Scalability

- Able to scale with increasing user load without major redesign.

### NFR4: Usability

- Intuitive, accessible, and mobile-responsive interface.

---

## 🗃️ 5. Database Design

### 5.1 User

```sql
User (
  user_id INT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  password VARCHAR(255),
  phone VARCHAR(20),
  date_of_birth DATE,
  passport_id VARCHAR(100),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)

Instructor (
  instructor_id INT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  phone VARCHAR(20),
  license_number VARCHAR(50),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)

Exam (
  exam_id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT,
  slot_id INT,
  result ENUM('pass', 'fail', 'pending'),
  remarks TEXT,
  review TEXT,
  experation_at DATETIME,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES User(user_id),
  FOREIGN KEY (slot_id) REFERENCES AvailableSlot(slot_id)
)

DriverLicense (
  license_id INT PRIMARY KEY,
  user_id INT UNIQUE,
  license_number VARCHAR(50) UNIQUE,
  issue_date DATE,
  expiry_date DATE,
  status ENUM('active', 'expired', 'revoked'),
  category ENUM('A', 'B', 'C', 'D'),
  FOREIGN KEY (user_id) REFERENCES User(user_id)
)

ExamDay (
  day_id INT PRIMARY KEY AUTO_INCREMENT,
  date DATE UNIQUE,
  instructor_id INT,
  max_students INT,
  current_students INT DEFAULT 0,
  exam_type ENUM('theoretical', 'practical'),
  category ENUM('A', 'B', 'C', 'D'),
  FOREIGN KEY (instructor_id) REFERENCES Instructor(instructor_id)
)

AvailableSlot (
  slot_id INT PRIMARY KEY,
  exam_day_id INT,
  instructor_id INT,
  time TIME,
  is_booked BOOLEAN DEFAULT FALSE,
  FOREIGN KEY (exam_day_id) REFERENCES ExamDay(day_id),
  FOREIGN KEY (instructor_id) REFERENCES Instructor(instructor_id)
)

```

## 📎 6. Appendices
### 6.1 Glossary
-  Slot: A specific time on a given exam day when an exam can be scheduled.

- ExamDay: A calendar day with available exam slots and a student limit.

### 6.2 Technologies
- Backend: Spring, Spring Boot

- Frontend: HTML/CSS/JS (React or Vue preferred)

- Database: PostgreSQL

📝 This system improves efficiency in managing auto school operations and provides a better experience for both students and staff.
