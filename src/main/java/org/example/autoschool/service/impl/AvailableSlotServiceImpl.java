package org.example.autoschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.AvailableSlotDtoRequest;
import org.example.autoschool.dto.response.AvailableSlotDto;
import org.example.autoschool.entity.AvailableSlot;
import org.example.autoschool.enums.ExamType;
import org.example.autoschool.repository.AvailableSlotRepository;
import org.example.autoschool.service.AvailableSlotService;
import org.example.autoschool.utils.exception.AlreadyExistException;
import org.example.autoschool.utils.exception.ObjectNotFoundException;
import org.example.autoschool.utils.exception.OverloadExeption;
import org.example.autoschool.utils.mapper.AvailableSlotMapper;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailableSlotServiceImpl implements AvailableSlotService {
    private final AvailableSlotRepository slotRepository;
    private final AvailableSlotMapper slotMapper;

    @Override
    public AvailableSlot getEntityById(Long id) {
        return slotRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Branch with id: " + id + "not found"));
    }

    @Override
    public AvailableSlotDto getDtoById(Long id) {
        return slotMapper.toDto(getEntityById(id));
    }

    @Override
    public List<AvailableSlotDto> getAllDtos() {
        return slotMapper.toDtoList(slotRepository.findAll());
    }

    @Override
    public List<AvailableSlotDto> getDtoByExamDayId(Long id) {
        return slotMapper.toDtoList(slotRepository.getDtoByExamDayId(id));
    }

    @Override
    public List<AvailableSlotDto> getDtoByInstructorId(Long id) {
        return slotMapper.toDtoList(slotRepository.getDtoByInstructorId(id));
    }

    @Override
    public List<AvailableSlotDto> getDtoByIsBooked(Boolean isBooked) {
        return slotMapper.toDtoList(slotRepository.getDtoByIsBooked(isBooked));
    }

    @Override
    public List<AvailableSlotDto> getDtoByTime(String time) {
        return slotMapper.toDtoList(slotRepository.getDtoByTime(LocalTime.parse(time)));
    }

    @Override
    public List<AvailableSlotDto> getDtoByExamDayIdAndInstructorIdAndIsBookedAndTime(Long examDayId, Long instructorId, Boolean isBooked, String time) {
        return slotMapper.toDtoList(
                slotRepository.getDtoByExamDayIdAndInstructorIdAndIsBookedAndTime(
                        examDayId, instructorId, isBooked, LocalTime.parse(time)));
    }

    @Override
    public List<AvailableSlotDto> getDtoByExamDayIdAndInstructorIdAndIsBooked(Long examDayId, Long instructorId, Boolean isBooked) {
        return slotMapper.toDtoList(
                slotRepository.getDtoByExamDayIdAndInstructorIdAndIsBooked(
                        examDayId, instructorId, isBooked));
    }

    @Override
    public List<AvailableSlotDto> getDtoByExamDayIdAndInstructorIdAndTime(Long examDayId, Long instructorId, String time) {
        return slotMapper.toDtoList(
                slotRepository.getDtoByExamDayIdAndInstructorIdAndTime(
                        examDayId, instructorId, LocalTime.parse(time)));
    }

    @Override
    public List<AvailableSlotDto> getDtoByExamDayIdAndIsBookedAndTime(Long examDayId, Boolean isBooked, String time) {
        return slotMapper.toDtoList(
                slotRepository.getDtoByExamDayIdAndIsBookedAndTime(
                        examDayId, isBooked, LocalTime.parse(time)));
    }

    @Override
    public List<AvailableSlotDto> getDtoByInstructorIdAndisBookedAndTime(Long instructorId, Boolean isBooked, String time) {
        return slotMapper.toDtoList(
                slotRepository.getDtoByInstructorIdAndIsBookedAndTime(
                        instructorId, isBooked, LocalTime.parse(time)));
    }

    @Override
    public List<AvailableSlotDto> getDtoByExamDayIdAndInstructorId(Long examDayId, Long instructorId) {
        return slotMapper.toDtoList(
                slotRepository.getDtoByExamDayIdAndInstructorId(examDayId, instructorId));
    }

    @Override
    public List<AvailableSlotDto> getDtoByExamDayIdAndIsBooked(Long examDayId, Boolean isBooked) {
        return slotMapper.toDtoList(
                slotRepository.getDtoByExamDayIdAndIsBooked(examDayId, isBooked));
    }

    @Override
    public List<AvailableSlotDto> getDtoByExamDayIdAndTime(Long examDayId, String time) {
        return slotMapper.toDtoList(
                slotRepository.getDtoByExamDayIdAndTime(examDayId, LocalTime.parse(time)));
    }

    @Override
    public List<AvailableSlotDto> getDtoByInstructorIdAndIsBooked(Long instructorId, Boolean isBooked) {
        return slotMapper.toDtoList(
                slotRepository.getDtoByInstructorIdAndIsBooked(instructorId, isBooked));
    }

    @Override
    public List<AvailableSlotDto> getDtoByInstructorIdAndTime(Long instructorId, String time) {
        return slotMapper.toDtoList(
                slotRepository.getDtoByInstructorIdAndTime(instructorId, LocalTime.parse(time)));
    }

    @Override
    public List<AvailableSlotDto> getDtoByIsBookedAndTime(Boolean isBooked, String time) {
        return slotMapper.toDtoList(
                slotRepository.getDtoByIsBookedAndTime(isBooked, LocalTime.parse(time)));
    }

    @Override
    public AvailableSlotDto save(AvailableSlotDtoRequest request) {
        AvailableSlot availableSlot = slotMapper.toEntity(request);

        if (availableSlot.getExamDay().getExamType() == ExamType.PRACTICAL)
            availableSlot.setMaxStudentNumber(1);

        if (slotRepository.existsByExamDayIdAndInstructorIdAndTime(
                availableSlot.getExamDay().getId(), availableSlot.getInstructor().getId(), availableSlot.getTime()))
            throw new AlreadyExistException("Slot" + "exam day id, instructor id, time" +
                    availableSlot.getExamDay().getId() + " " + availableSlot.getInstructor().getId() + " " + availableSlot.getTime());

        return slotMapper.toDto(slotRepository.save(availableSlot));
    }

    @Override
    public AvailableSlotDto update(AvailableSlotDtoRequest request) {
        AvailableSlot slot = slotMapper.toEntity(request);
        AvailableSlot newSlot = getEntityById(request.getId());

        newSlot.setExamDay(slot.getExamDay());
        newSlot.setInstructor(slot.getInstructor());
        newSlot.setTime(slot.getTime());
        newSlot.setIsBooked(slot.getIsBooked());
        newSlot.setMaxStudentNumber(slot.getMaxStudentNumber());

        if (getDtoByExamDayIdAndInstructorIdAndTime(
                slot.getExamDay().getId(), slot.getInstructor().getId(),
                String.valueOf(slot.getTime())).size() > slot.getMaxStudentNumber())
            throw new OverloadExeption("This slot already have max student number");
        return null;
    }

    @Override
    public void bookSlot(Long slotId) {
        AvailableSlot slot = slotRepository.getById(slotId);
        slot.setIsBooked(true);
        slotRepository.save(slot);
    }
}
