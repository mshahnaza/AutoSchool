package org.example.autoschool.repository;

import org.example.autoschool.entity.Branch;
import org.example.autoschool.entity.ExamDay;
import org.example.autoschool.enums.Category;
import org.example.autoschool.enums.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExamDayRepository extends JpaRepository<ExamDay, Long> {
    List<ExamDay> findByDate(LocalDate date);
    List<ExamDay> findByCategory(Category category);
    List<ExamDay> findByExamType(ExamType examType);
    List<ExamDay> findByBranchId(Long branchId);

    boolean existsByDateAndExamTypeAndCategoryAndBranch(LocalDate date, ExamType examType, Category category, Branch branch);

    List<ExamDay> findByDateAndExamTypeAndCategory(LocalDate date, ExamType examType, Category category);
    List<ExamDay> findByDateAndExamType(LocalDate date, ExamType examType);
    List<ExamDay> findByDateAndCategory(LocalDate date, Category category);
    List<ExamDay> findByExamTypeAndCategory(ExamType examType, Category category);
}
