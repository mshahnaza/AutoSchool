package org.example.autoschool.dto.request;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ExamDtoRequest {
    private Long id;
    private String result;
    private String remarks;
    private LocalDate expirationAt;
    private LocalDate takenAt;
    private Long studentId;
    private Long slotId;
}
