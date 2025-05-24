CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    date_of_birth DATE,
    created_at TIMESTAMP,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS tokens (
    id BIGINT PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    token_type VARCHAR(50) NOT NULL,
    created_at TIME NOT NULL,
    expires_at TIME NOT NULL,
    is_expired BOOLEAN NOT NULL DEFAULT FALSE,
    is_revoked BOOLEAN NOT NULL DEFAULT FALSE,
    user_id BIGINT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS student (
    id BIGINT PRIMARY KEY,
    passport_id VARCHAR(255) NOT NULL,
    user_id BIGINT UNIQUE,
    CONSTRAINT fk_student_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS instructor (
    id BIGINT PRIMARY KEY,
    license_number VARCHAR(255) NOT NULL,
    user_id BIGINT UNIQUE,
    CONSTRAINT fk_instructor_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS branch (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS exam_day (
    id BIGINT PRIMARY KEY,
    date DATE NOT NULL,
    max_students INTEGER DEFAULT 30,
    current_students INTEGER DEFAULT 0,
    exam_type VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    branch_id BIGINT NOT NULL,
    CONSTRAINT fk_exam_day_branch FOREIGN KEY (branch_id) REFERENCES branch(id)
);

CREATE TABLE IF NOT EXISTS available_slot (
    id BIGINT PRIMARY KEY,
    time TIME NOT NULL,
    max_student_number INTEGER NOT NULL,
    is_booked BOOLEAN NOT NULL DEFAULT FALSE,
    exam_day_id BIGINT NOT NULL,
    instructor_id BIGINT NOT NULL,
    CONSTRAINT fk_available_slot_exam_day FOREIGN KEY (exam_day_id) REFERENCES exam_day(id),
    CONSTRAINT fk_available_slot_instructor FOREIGN KEY (instructor_id) REFERENCES instructor(id)
);

CREATE TABLE IF NOT EXISTS exam (
    id BIGINT PRIMARY KEY,
    result VARCHAR(255),
    remarks VARCHAR(255),
    expiration_at DATE,
    taken_at DATE NOT NULL,
    student_id BIGINT NOT NULL,
    slot_id BIGINT NOT NULL,
    CONSTRAINT fk_exam_student FOREIGN KEY (student_id) REFERENCES student(id),
    CONSTRAINT fk_exam_slot FOREIGN KEY (slot_id) REFERENCES available_slot(id)
);


CREATE TABLE IF NOT EXISTS driver_licence (
    id BIGINT PRIMARY KEY,
    licence_number VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    issue_date DATE NOT NULL,
    expiry_date DATE NOT NULL,
    driver_id BIGINT NOT NULL,
    CONSTRAINT fk_driver_licence_driver FOREIGN KEY (driver_id) REFERENCES student(id)
);



