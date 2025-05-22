package org.example.autoschool.service;

import org.example.autoschool.dto.request.ExamDayDtoRequest;
import org.example.autoschool.dto.response.ExamDayDto;
import org.example.autoschool.entity.ExamDay;

public interface ExamDayService {
    ExamDay getEntityById(Long id);
    ExamDayDto getDtoById(Long id);

    ExamDayDto save(ExamDayDtoRequest request);
    ExamDayDto update(ExamDayDtoRequest request);
}
