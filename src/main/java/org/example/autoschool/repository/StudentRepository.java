package org.example.autoschool.repository;

import org.example.autoschool.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.studentUser.email = :email")
    Optional<Student> findByUserEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.studentUser.id = :userId")
    Optional<Student> findByUserId(Long userId);
}
