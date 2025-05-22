package org.example.autoschool.utils.mapper;

import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.AvailableSlotDtoRequest;
import org.example.autoschool.dto.response.AvailableSlotDto;
import org.example.autoschool.entity.AvailableSlot;
import org.example.autoschool.service.ExamDayService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AvailableSlotMapper {
    private final ExamDayService examDayService;
    private final ExamDayMapper examDayMapper;

    public AvailableSlotDto toDto(AvailableSlot slot) {
        return new AvailableSlotDto().toBuilder()
                .id(slot.getId())
                .time(slot.getTime())
                .isBooked(slot.getIsBooked())
                .maxStudentNumber(slot.getMaxStudentNumber())
                .examDay(examDayMapper.toDto(slot.getExamDay()))
                .build();
    }

    public List<AvailableSlotDto> toDtoList(List<AvailableSlot> slots) {
        return slots.stream().map(this::toDto).collect(Collectors.toList());
    }

    public AvailableSlot toEntity(AvailableSlotDtoRequest slotDtoRequest) {
        return new AvailableSlot().toBuilder()
                .id(slotDtoRequest.getId())
                .time(slotDtoRequest.getTime())
                .isBooked(slotDtoRequest.getIsBooked())
                .maxStudentNumber(slotDtoRequest.getMaxStudentNumber())
                .examDay(examDayService.getEntityById(slotDtoRequest.getExamDayId()))
                .build();
    }
}
