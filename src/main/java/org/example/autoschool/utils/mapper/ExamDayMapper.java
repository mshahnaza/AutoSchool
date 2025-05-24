package org.example.autoschool.utils.mapper;

import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.ExamDayDtoRequest;
import org.example.autoschool.dto.response.ExamDayDto;
import org.example.autoschool.entity.ExamDay;
import org.example.autoschool.enums.Category;
import org.example.autoschool.enums.ExamType;
import org.example.autoschool.service.BranchService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExamDayMapper {
    private final BranchMapper branchMapper;
    private final BranchService branchService;

    public ExamDayDto toDto(ExamDay examDay) {
        return new ExamDayDto().toBuilder()
                .id(examDay.getId())
                .date(examDay.getDate())
                .maxStudents(examDay.getMaxStudents())
                .currentStudents(examDay.getCurrentStudents())
                .examType(examDay.getExamType())
                .category(examDay.getCategory())
                .branch(branchMapper.toDto(examDay.getBranch()))
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
                .branch(branchService.getEntityById(examDayDtoRequest.getBrunchId()))
                .build();
    }
}
