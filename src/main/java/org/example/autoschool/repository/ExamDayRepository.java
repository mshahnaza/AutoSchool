package org.example.autoschool.repository;

import org.example.autoschool.entity.Exam;
import org.example.autoschool.entity.ExamDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamDayRepository extends JpaRepository<ExamDay, Long> {
}
