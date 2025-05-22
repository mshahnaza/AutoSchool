package org.example.autoschool.utils.mapper;

import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.ExamDayDtoRequest;
import org.example.autoschool.dto.response.ExamDayDto;
import org.example.autoschool.entity.ExamDay;
import org.example.autoschool.enums.Category;
import org.example.autoschool.enums.ExamType;
import org.example.autoschool.service.InstructorService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExamDayMapper {
    private final InstructorService instructorService;
    private final InstructorMapper instructorMapper;

    public ExamDayDto toDto(ExamDay examDay) {
        return new ExamDayDto().toBuilder()
                .id(examDay.getId())
                .date(examDay.getDate())
                .maxStudents(examDay.getMaxStudents())
                .currentStudents(examDay.getCurrentStudents())
                .examType(examDay.getExamType())
                .category(examDay.getCategory())
                .instructor(instructorMapper.toDto(examDay.getInstructor()))
                .build();
    }

    public List<ExamDayDto> toDtoList(List<ExamDay> examDays) {
        return examDays.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ExamDay toEntity(ExamDayDtoRequest examDayDtoRequest) {
        return new ExamDay().toBuilder()
                .id(examDayDtoRequest.getId())
                .date(examDayDtoRequest.getDate())
                .maxStudents(examDayDtoRequest.getMaxStudents())
                .examType(ExamType.valueOf(examDayDtoRequest.getExamType()))
                .category(Category.valueOf(examDayDtoRequest.getCategory()))
                .instructor(instructorService.getEntityById(examDayDtoRequest.getInstructorId()))
                .build();
    }
}
