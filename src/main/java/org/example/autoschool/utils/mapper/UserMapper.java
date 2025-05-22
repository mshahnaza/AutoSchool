package org.example.autoschool.utils.mapper;

import lombok.NoArgsConstructor;
import org.example.autoschool.dto.request.UserDtoRequest;
import org.example.autoschool.dto.response.UserDto;
import org.example.autoschool.entity.User;
import org.example.autoschool.enums.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto().toBuilder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .dateOfBirth(user.getDateOfBirth())
                .createdAt(user.getCreatedAt())
                .role(user.getRole())
                .build();
    }

    public List<UserDto> toDtoList(List<User> tasks) {
        return tasks.stream().map(this::toDto).collect(Collectors.toList());
    }

    public User toEntity(UserDtoRequest userDtoRequest) {
        return new User().toBuilder()
                .id(userDtoRequest.getId())
                .name(userDtoRequest.getName())
                .email(userDtoRequest.getEmail())
                .password(userDtoRequest.getPassword())
                .phone(userDtoRequest.getPhone())
                .role(Role.valueOf(userDtoRequest.getRole()))
                .build();
    }
}
