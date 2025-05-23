package org.example.autoschool.dto.response;

import lombok.*;

import java.time.LocalTime;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AvailableSlotDto {
    private Long id;
    private LocalTime time;
    private Integer maxStudentNumber;
    private Boolean isBooked;
    private ExamDayDto examDay;
    private InstructorDto instructor;
}
