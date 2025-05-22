package org.example.autoschool.service;

import org.example.autoschool.dto.request.ExamDtoRequest;
import org.example.autoschool.dto.response.ExamDto;
import org.example.autoschool.entity.Exam;

public interface ExamService {
    Exam getEntityById(Long id);
    ExamDto getDtoById(Long id);

    ExamDto save(ExamDtoRequest request);
    ExamDto update(ExamDtoRequest request);
}
