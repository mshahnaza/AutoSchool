package org.example.autoschool.dto.request;

import lombok.*;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class BranchDtoRequest {
    private Long id;
    private String name;
    private String address;
    private String phone;
}
