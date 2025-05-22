package org.example.autoschool.repository;

import org.example.autoschool.entity.Instructor;
import org.example.autoschool.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
