package org.example.autoschool.repository;

import org.example.autoschool.entity.AvailableSlot;
import org.example.autoschool.entity.ExamDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface AvailableSlotRepository extends JpaRepository<AvailableSlot, Long> {
    Boolean existsByExamDayIdAndInstructorIdAndTime(Long examDayId, Long instructorId, LocalTime time);

    List<AvailableSlot> getDtoByExamDayId(Long id);

    List<AvailableSlot> getDtoByInstructorId(Long id);

    List<AvailableSlot> getDtoByIsBooked(Boolean isBooked);

    List<AvailableSlot> getDtoByTime(LocalTime time);

    List<AvailableSlot> getDtoByExamDayIdAndInstructorIdAndIsBookedAndTime(
            Long examDayId, Long instructorId, Boolean isBooked, LocalTime time);

    List<AvailableSlot> getDtoByExamDayIdAndInstructorIdAndIsBooked(
            Long examDayId, Long instructorId, Boolean isBooked);

    List<AvailableSlot> getDtoByExamDayIdAndInstructorIdAndTime(
            Long examDayId, Long instructorId, LocalTime time);

    List<AvailableSlot> getDtoByExamDayIdAndIsBookedAndTime(
            Long examDayId, Boolean isBooked, LocalTime time);

    List<AvailableSlot> getDtoByInstructorIdAndIsBookedAndTime(
            Long instructorId, Boolean isBooked, LocalTime time);

    List<AvailableSlot> getDtoByExamDayIdAndInstructorId(
            Long examDayId, Long instructorId);

    List<AvailableSlot> getDtoByExamDayIdAndIsBooked(
            Long examDayId, Boolean isBooked);

    List<AvailableSlot> getDtoByExamDayIdAndTime(
            Long examDayId, LocalTime time);

    List<AvailableSlot> getDtoByInstructorIdAndIsBooked(
            Long instructorId, Boolean isBooked);

    List<AvailableSlot> getDtoByInstructorIdAndTime(
            Long instructorId, LocalTime time);

    List<AvailableSlot> getDtoByIsBookedAndTime(
            Boolean isBooked, LocalTime time);

}
