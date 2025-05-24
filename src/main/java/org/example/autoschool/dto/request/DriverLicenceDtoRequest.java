package org.example.autoschool.dto.request;

import lombok.*;
import org.example.autoschool.enums.Status;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DriverLicenceDtoRequest {
    private Long id;
    private String licenceNumber;
    private String category;
    private Status status;
    private String issueDate;
    private String expiryDate;
    private Long studentId;
}
