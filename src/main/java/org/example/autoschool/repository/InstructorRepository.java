package org.example.autoschool.repository;

import org.example.autoschool.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Optional<Instructor> findInstructorByLicenseNumber(String licenseNumber);

    @Query("SELECT i FROM Instructor i WHERE i.instructorUser.email = :email")
    Optional<Instructor> findByUserEmail(String email);

    @Query("SELECT i FROM Instructor i WHERE i.instructorUser.id = :userId")
    Optional<Instructor> findByUserId(Long userId);

    @Query("SELECT i FROM Instructor i " +
            "JOIN i.slots s " +
            "JOIN Exam e ON e.availableSlot = s " +
            "WHERE e.id = :examId")
    Instructor findInstructorByExamId(@Param("examId") Long examId);
}
