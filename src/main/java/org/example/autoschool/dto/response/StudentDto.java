package org.example.autoschool.dto.response;

import lombok.*;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long id;
    private String passportId;
    private UserDto user;
}
