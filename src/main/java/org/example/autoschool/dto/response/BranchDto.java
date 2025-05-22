package org.example.autoschool.dto.response;

import lombok.*;

@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
}
