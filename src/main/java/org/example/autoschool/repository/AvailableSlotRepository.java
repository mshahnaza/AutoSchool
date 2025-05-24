package org.example.autoschool.repository;

import org.example.autoschool.entity.AvailableSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface AvailableSlotRepository extends JpaRepository<AvailableSlot, Long> {
    Boolean existsByExamDayIdAndInstructorIdAndTime(Long examDayId, Long instructorId, LocalTime time);

    List<AvailableSlot> findDtoByExamDayId(Long id);

    List<AvailableSlot> findDtoByInstructorId(Long id);

    List<AvailableSlot> findDtoByIsBooked(Boolean isBooked);

    List<AvailableSlot> findDtoByTime(LocalTime time);

    List<AvailableSlot> findDtoByExamDayIdAndInstructorIdAndIsBookedAndTime(
            Long examDayId, Long instructorId, Boolean isBooked, LocalTime time);

    List<AvailableSlot> findDtoByExamDayIdAndInstructorIdAndIsBooked(
            Long examDayId, Long instructorId, Boolean isBooked);

    List<AvailableSlot> findDtoByExamDayIdAndInstructorIdAndTime(
            Long examDayId, Long instructorId, LocalTime time);

    List<AvailableSlot> findDtoByExamDayIdAndIsBookedAndTime(
            Long examDayId, Boolean isBooked, LocalTime time);

    List<AvailableSlot> findDtoByInstructorIdAndIsBookedAndTime(
            Long instructorId, Boolean isBooked, LocalTime time);

    List<AvailableSlot> findDtoByExamDayIdAndInstructorId(
            Long examDayId, Long instructorId);

    List<AvailableSlot> findDtoByExamDayIdAndIsBooked(
            Long examDayId, Boolean isBooked);

    List<AvailableSlot> findDtoByExamDayIdAndTime(
            Long examDayId, LocalTime time);

    List<AvailableSlot> findDtoByInstructorIdAndIsBooked(
            Long instructorId, Boolean isBooked);

    List<AvailableSlot> findDtoByInstructorIdAndTime(
            Long instructorId, LocalTime time);

    List<AvailableSlot> findDtoByIsBookedAndTime(
            Boolean isBooked, LocalTime time);

}
