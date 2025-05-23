package org.example.autoschool.dto.request;

import lombok.*;

import java.time.LocalTime;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AvailableSlotDtoRequest {
    private Long id;
    private LocalTime time;
    private Integer maxStudentNumber;
    private Boolean isBooked;
    private Long examDayId;
    private Long instructorId;
}
