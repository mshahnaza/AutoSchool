package org.example.autoschool.dto.request;

import lombok.*;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoRequest {
    private Long id;
    private String passportId;
    private UserDtoRequest user;
}
