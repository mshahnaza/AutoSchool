package org.example.autoschool.dto.response;

import lombok.*;
import org.example.autoschool.enums.Category;
import org.example.autoschool.enums.Status;

import java.time.LocalDate;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DriverLicenceDto {
    private Long id;
    private String licenceNumber;
    private Category category;
    private Status status;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private StudentDto driver;
}
