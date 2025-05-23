package org.example.autoschool.repository;

import org.example.autoschool.entity.Exam;
import org.example.autoschool.enums.Category;
import org.example.autoschool.enums.ExamResult;
import org.example.autoschool.enums.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query("SELECT e FROM Exam e WHERE e.student.id = :studentId")
    List<Exam> findByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT e FROM Exam e WHERE e.availableSlot.id = :slotId")
    List<Exam> findBySlotId(@Param("slotId") Long slotId);

    @Query("SELECT COUNT(e) FROM Exam e WHERE e.availableSlot.id = :slotId")
    Integer countBySlotId(@Param("slotId") Long slotId);

    @Query("SELECT e FROM Exam e WHERE e.availableSlot.instructor.id = :instructorId")
    List<Exam> findByInstructorId(@Param("instructorId") Long instructorId);

    @Query("SELECT e FROM Exam e WHERE e.availableSlot.examDay.category = :category")
    List<Exam> findByCategory(@Param("category") Category category);

    @Query("SELECT e FROM Exam e WHERE e.availableSlot.examDay.examType = :examType")
    List<Exam> findByExamType(@Param("examType") ExamType examType);

    @Query("SELECT e FROM Exam e WHERE e.availableSlot.examDay.date = :date")
    List<Exam> findByDate(@Param("date") LocalDate date);

    @Query("SELECT e FROM Exam e WHERE e.student.id = :studentId AND e.availableSlot.examDay.examType = :examType AND e.result = :result")
    List<Exam> findByStudentIdAndExamTypeAndResult(
            @Param("studentId") Long studentId,
            @Param("examType") ExamType examType,
            @Param("result") ExamResult result);

    @Query("SELECT e FROM Exam e WHERE e.student.id = :studentId AND e.result = :result")
    List<Exam> findByStudentIdAndResult(
            @Param("studentId") Long studentId,
            @Param("result") ExamResult result);

    @Query("SELECT e FROM Exam e WHERE e.availableSlot.instructor.id = :instructorId AND e.result = :result")
    List<Exam> findByInstructorIdAndResult(
            @Param("instructorId") Long instructorId,
            @Param("result") ExamResult result);

    @Query("SELECT e FROM Exam e WHERE e.availableSlot.instructor.id = :instructorId AND e.availableSlot.examDay.date = :date")
    List<Exam> findByInstructorIdAndDate(
            @Param("instructorId") Long instructorId,
            @Param("date") LocalDate date);

    @Query("SELECT e FROM Exam e WHERE e.availableSlot.examDay.category = :category AND e.availableSlot.examDay.date = :date")
    List<Exam> findByCategoryAndDate(
            @Param("category") Category category,
            @Param("date") LocalDate date);

    @Query("SELECT e FROM Exam e WHERE e.availableSlot.examDay.examType = :examType AND e.availableSlot.examDay.date = :date")
    List<Exam> findByExamTypeAndDate(
            @Param("examType") ExamType examType,
            @Param("date") LocalDate date);
}
