package org.example.autoschool.service;

import org.example.autoschool.dto.request.ExamDayDtoRequest;
import org.example.autoschool.dto.response.ExamDayDto;
import org.example.autoschool.entity.ExamDay;

import java.util.List;

public interface ExamDayService {
    ExamDay getEntityById(Long id);
    ExamDayDto getDtoById(Long id);

    List<ExamDayDto> getAllDtos();

    List<ExamDayDto> getDtoByDate(String date);
    List<ExamDayDto> getDtoByCategory(String category);
    List<ExamDayDto> getDtoByExamType(String examType);
    List<ExamDayDto> getDtoByBranchId(Long branchId);

    List<ExamDayDto> getByDateAndExamTypeAndCategory(String date, String examType, String category);
    List<ExamDayDto> getByDateAndExamType(String date, String examType);
    List<ExamDayDto> getByDateAndCategory(String date, String category);
    List<ExamDayDto> getByExamTypeAndCategory(String examType, String category);

    ExamDayDto save(ExamDayDtoRequest request);
    ExamDayDto update(ExamDayDtoRequest request);
}
