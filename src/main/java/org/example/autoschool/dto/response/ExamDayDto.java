package org.example.autoschool.dto.response;

import lombok.*;
import org.example.autoschool.enums.Category;
import org.example.autoschool.enums.ExamType;

import java.time.LocalDate;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ExamDayDto {
    private Long id;
    private LocalDate date;
    private Integer maxStudents;
    private Integer currentStudents;
    private ExamType examType;
    private Category category;
    private InstructorDto instructor;
    private BranchDto branch;
}
