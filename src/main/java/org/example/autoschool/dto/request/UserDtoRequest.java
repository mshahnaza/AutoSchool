package org.example.autoschool.dto.request;

import lombok.*;
import org.example.autoschool.enums.Role;

import java.time.LocalDate;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoRequest {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String role;
}
