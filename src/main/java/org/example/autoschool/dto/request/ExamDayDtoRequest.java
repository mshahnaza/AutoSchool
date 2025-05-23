package org.example.autoschool.dto.request;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ExamDayDtoRequest {
    private Long id;
    private LocalDate date;
    private Integer maxStudents;
    private String examType;
    private String category;
    private Long brunchId;
}
