package org.example.autoschool.dto.response;

import lombok.*;
import org.example.autoschool.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
    private Role role;
}
