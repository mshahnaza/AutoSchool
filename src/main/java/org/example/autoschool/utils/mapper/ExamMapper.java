package org.example.autoschool.utils.mapper;

import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.AvailableSlotDtoRequest;
import org.example.autoschool.dto.request.ExamDtoRequest;
import org.example.autoschool.dto.response.AvailableSlotDto;
import org.example.autoschool.dto.response.ExamDto;
import org.example.autoschool.entity.AvailableSlot;
import org.example.autoschool.entity.Exam;
import org.example.autoschool.enums.ExamResult;
import org.example.autoschool.service.AvailableSlotService;
import org.example.autoschool.service.ExamDayService;
import org.example.autoschool.service.StudentService;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExamMapper {
    private final AvailableSlotService availableSlotService;
    private final StudentService studentService;
    private final AvailableSlotMapper availableSlotMapper;
    private final StudentMapper studentMapper;

    public ExamDto toDto(Exam exam) {
        return new ExamDto().toBuilder()
                .id(exam.getId())
                .takenAt(exam.getTakenAt())
                .expirationAt(exam.getExpirationAt())
                .result(exam.getResult())
                .remarks(exam.getRemarks())
                .student(studentMapper.toDto(exam.getStudent()))
                .slot(availableSlotMapper.toDto(exam.getAvailableSlot()))
                .build();
    }

    public List<ExamDto> toDtoList(List<Exam> exams) {
        return exams.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Exam toEntity(ExamDtoRequest examDtoRequest) {
        return new Exam().toBuilder()
                .id(examDtoRequest.getId())
                .takenAt(examDtoRequest.getTakenAt())
                .expirationAt(examDtoRequest.getExpirationAt())
                .result(ExamResult.valueOf(examDtoRequest.getResult()))
                .remarks(examDtoRequest.getRemarks())
                .student(studentService.getEntityById(examDtoRequest.getStudentId()))
                .slot(studentService.getEntityById(examDtoRequest.getSlotId()))
                .build();
    }
}
