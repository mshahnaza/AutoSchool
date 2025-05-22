package org.example.autoschool.dto.response;

import lombok.*;
import org.example.autoschool.enums.ExamResult;

import java.time.LocalDate;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ExamDto {
    private Long id;
    private ExamResult result;
    private String remarks;
    private LocalDate expirationAt;
    private LocalDate takenAt;
    private StudentDto student;
    private AvailableSlotDto slot;
}
