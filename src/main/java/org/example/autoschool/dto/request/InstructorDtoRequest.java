package org.example.autoschool.dto.request;

import lombok.*;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDtoRequest {
    private Long id;
    private String licenseNumber;
    private UserDtoRequest user;
}
