package org.example.autoschool.dto.response;

import lombok.*;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDto {
    private Long id;
    private String licenseNumber;
    private UserDto user;
}
