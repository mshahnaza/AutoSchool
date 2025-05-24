package org.example.autoschool.service;

import org.example.autoschool.dto.request.AvailableSlotDtoRequest;
import org.example.autoschool.dto.response.AvailableSlotDto;
import org.example.autoschool.entity.AvailableSlot;

import java.util.List;

public interface AvailableSlotService {
    AvailableSlot getEntityById(Long id);
    AvailableSlotDto getDtoById(Long id);

    List<AvailableSlotDto> getAllDtos();

    List<AvailableSlotDto> getDtoByExamDayId(Long id);
    List<AvailableSlotDto> getDtoByInstructorId(Long id);
    List<AvailableSlotDto> getDtoByIsBooked(Boolean isBooked);
    List<AvailableSlotDto> getDtoByTime(String time);

    List<AvailableSlotDto> getDtoByExamDayIdAndInstructorIdAndIsBookedAndTime(Long examDayId, Long instructorId, Boolean isBooked, String time);

    List<AvailableSlotDto> getDtoByExamDayIdAndInstructorIdAndIsBooked(Long examDayId, Long instructorId, Boolean isBooked);
    List<AvailableSlotDto> getDtoByExamDayIdAndInstructorIdAndTime(Long examDayId, Long instructorId, String time);
    List<AvailableSlotDto> getDtoByExamDayIdAndIsBookedAndTime(Long examDayId, Boolean isBooked, String time);
    List<AvailableSlotDto> getDtoByInstructorIdAndisBookedAndTime(Long instructorId, Boolean isBooked, String time);

    List<AvailableSlotDto> getDtoByExamDayIdAndInstructorId(Long examDayId, Long instructorId);
    List<AvailableSlotDto> getDtoByExamDayIdAndIsBooked(Long examDayId, Boolean isBooked);
    List<AvailableSlotDto> getDtoByExamDayIdAndTime(Long examDayId, String time);
    List<AvailableSlotDto> getDtoByInstructorIdAndIsBooked(Long instructorId, Boolean isBooked);
    List<AvailableSlotDto> getDtoByInstructorIdAndTime(Long instructorId, String time);
    List<AvailableSlotDto> getDtoByIsBookedAndTime(Boolean isBooked, String time);

    AvailableSlotDto save(AvailableSlotDtoRequest request);
    AvailableSlotDto update(AvailableSlotDtoRequest request);

    void bookSlot(Long slotId);
}
