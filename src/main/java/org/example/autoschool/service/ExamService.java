package org.example.autoschool.service;

import org.example.autoschool.dto.request.ExamDtoRequest;
import org.example.autoschool.dto.response.ExamDto;
import org.example.autoschool.entity.Exam;

import java.util.List;

public interface ExamService {
    Exam getEntityById(Long id);
    ExamDto getDtoById(Long id);

    List<ExamDto> getAllDtos();

    List<ExamDto> getDtosByStudentId(Long id);
    List<ExamDto> getDtosBySlotId(Long id);
    List<ExamDto> getDtosByInstructorId(Long id);
    List<ExamDto> getDtosByCategory(String category);
    List<ExamDto> getDtosByExamType(String examType);
    List<ExamDto> getDtosByDate(String date);

    List<ExamDto> getDtosByStudentIdAndExamTypeAndResult(Long studentId, String examType, String result);
    List<ExamDto> getDtosByStudentIdAndResult(Long id, String result);
    List<ExamDto> getDtosByInstructorIdAndResult(Long id, String result);
    List<ExamDto> getDtosByInstructorIdAndDate(Long id, String date);
    List<ExamDto> getDtosByCategoryAndDate(String category, String date);
    List<ExamDto> getDtosByExamTypeAndDate(String examType, String date);

    List<Exam> getDtosByBeforeExperationDate(Integer dayNumber);
    List<Exam> getDtosByBeforeExamDate(Integer dayNumber);

    ExamDto save(ExamDtoRequest request);
    ExamDto update(ExamDtoRequest request);

    ExamDto giveGrade(Long examId, String result, String remarks);
}
