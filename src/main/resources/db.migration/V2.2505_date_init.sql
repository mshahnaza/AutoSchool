-- USERS
INSERT INTO users (id, name, email, phone, password, date_of_birth, created_at, role) VALUES
    (1, 'Alice Smith', 'alice@example.com', '1234567890', 'password1', '1990-01-01', '2025-05-01 10:00:00', 'STUDENT'),
    (2, 'Bob Johnson', 'bob@example.com', '0987654321', 'password2', '1985-02-15', '2025-05-01 11:00:00', 'INSTRUCTOR'),
    (3, 'Charlie Brown', 'charlie@example.com', '1112223333', 'password3', '1992-03-20', '2025-05-01 12:00:00', 'STUDENT'),
    (4, 'Diana Prince', 'diana@example.com', '4445556666', 'password4', '1988-04-10', '2025-05-02 09:30:00', 'ADMIN'),
    (5, 'Evan Wright', 'evan@example.com', '7778889999', 'password5', '1995-05-25', '2025-05-02 10:00:00', 'INSTRUCTOR'),
    (6, 'Fiona Gallagher', 'fiona@example.com', '2223334444', 'password6', '1991-06-30', '2025-05-03 08:45:00', 'STUDENT'),
    (7, 'George Martin', 'george@example.com', '5556667777', 'password7', '1983-07-05', '2025-05-03 09:15:00', 'STUDENT'),
    (8, 'Hannah Baker', 'hannah@example.com', '8889990000', 'password8', '1997-08-12', '2025-05-04 14:20:00', 'STUDENT');

-- TOKENS
INSERT INTO tokens (id, token, token_type, created_at, expires_at, is_expired, is_revoked, user_id) VALUES
    (1, 'token1', 'ACCESS', '2025-05-01 10:05:00', '2025-05-01 12:05:00', FALSE, FALSE, 1),
    (2, 'token2', 'REFRESH', '2025-05-01 10:06:00', '2025-06-01 10:06:00', FALSE, FALSE, 1),
    (3, 'token3', 'ACCESS', '2025-05-01 11:10:00', '2025-05-01 13:10:00', FALSE, FALSE, 2),
    (4, 'token4', 'REFRESH', '2025-05-01 11:15:00', '2025-06-01 11:15:00', FALSE, FALSE, 2),
    (5, 'token5', 'ACCESS', '2025-05-02 08:30:00', '2025-05-02 10:30:00', FALSE, FALSE, 3),
    (6, 'token6', 'REFRESH', '2025-05-02 08:35:00', '2025-06-02 08:35:00', FALSE, FALSE, 3),
    (7, 'token7', 'ACCESS', '2025-05-03 09:00:00', '2025-05-03 11:00:00', FALSE, FALSE, 4),
    (8, 'token8', 'REFRESH', '2025-05-03 09:05:00', '2025-06-03 09:05:00', FALSE, FALSE, 4);

-- STUDENT
INSERT INTO student (id, passport_id, user_id) VALUES
    (1, 'P1234567', 1),
    (2, 'P2345678', 3),
    (3, 'P3456789', 6),
    (4, 'P4567890', 7),
    (5, 'P5678901', 8);

-- INSTRUCTOR
INSERT INTO instructor (id, license_number, user_id) VALUES
    (1, 'LIC12345', 2),
    (2, 'LIC23456', 5);

-- BRANCH
INSERT INTO branch (id, name, address, phone) VALUES
    (1, 'Downtown Branch', '123 Main St', '555-1111'),
    (2, 'Uptown Branch', '456 Oak Ave', '555-2222');

-- EXAM_DAY
INSERT INTO exam_day (id, date, max_students, current_students, exam_type, category, branch_id) VALUES
    (1, '2025-06-01', 30, 10, 'THEORETICAL', 'A', 1),
    (2, '2025-06-02', 30, 5, 'PRACTICAL', 'B', 2),
    (3, '2025-06-03', 30, 8, 'THEORETICAL', 'C', 1),
    (4, '2025-06-04', 30, 12, 'PRACTICAL', 'A1', 2),
    (5, '2025-06-05', 30, 0, 'THEORETICAL', 'A', 1),
    (6, '2025-06-06', 30, 3, 'PRACTICAL', 'B', 2),
    (7, '2025-06-07', 30, 9, 'THEORETICAL', 'C', 1),
    (8, '2025-06-08', 30, 1, 'PRACTICAL', 'D', 2);

-- AVAILABLE_SLOT
INSERT INTO available_slot (id, time, max_student_number, is_booked, exam_day_id, instructor_id) VALUES
    (1, '09:00:00', 5, FALSE, 1, 1),
    (2, '10:00:00', 5, TRUE, 1, 1),
    (3, '11:00:00', 5, FALSE, 2, 2),
    (4, '12:00:00', 5, TRUE, 2, 2),
    (5, '13:00:00', 5, FALSE, 3, 1),
    (6, '14:00:00', 5, TRUE, 3, 1),
    (7, '15:00:00', 5, FALSE, 4, 2),
    (8, '16:00:00', 5, TRUE, 4, 2);

-- EXAM
INSERT INTO exam (id, result, remarks, expiration_at, taken_at, student_id, slot_id) VALUES
    (1, 'EXPIRED', 'Good performance', '2025-01-01', '2025-06-01', 1, 1),
    (2, 'FAILED', 'Needs improvement', '2025-09-02', '2025-06-01', 2, 2),
    (3, 'PENDING', '', '2025-09-03', '2025-06-02', 3, 3),
    (4, 'PASSED', 'Excellent', '2025-09-04', '2025-06-02', 4, 4),
    (5, 'FAILED', 'Retake required', '2025-09-05', '2025-06-03', 5, 5),
    (6, 'PASSED', 'Satisfactory', '2025-09-06', '2025-06-03', 2, 6),
    (7, 'PENDING', '', '2025-09-07', '2025-06-04', 5, 7),
    (8, 'PASSED', 'Well done', '2025-09-08', '2025-06-04', 1, 8);

-- DRIVER_LICENCE
INSERT INTO driver_licence (id, licence_number, category, status, issue_date, expiry_date, driver_id) VALUES
    (1, 'DL123456', 'B', 'ACTIVE', '2020-01-01', '2030-01-01', 1),
    (2, 'DL234567', 'C', 'EXPIRED', '2015-02-15', '2025-02-15', 2),
    (3, 'DL345678', 'A', 'ACTIVE', '2021-03-20', '2031-03-20', 3),
    (4, 'DL456789', 'D', 'ACTIVE', '2019-04-10', '2029-04-10', 4),
    (5, 'DL567890', 'B', 'RESOLVED', '2018-05-25', '2028-05-25', 5),
    (6, 'DL678901', 'C', 'ACTIVE', '2022-06-30', '2032-06-30', 2),
    (7, 'DL789012', 'A', 'EXPIRED', '2017-07-05', '2027-07-05', 5);