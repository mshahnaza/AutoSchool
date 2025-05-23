package org.example.autoschool.service;

import org.example.autoschool.dto.request.UserDtoRequest;
import org.example.autoschool.dto.response.UserDto;
import org.example.autoschool.entity.User;

import java.util.List;

public interface UserService {
    User getEntityById(Long id);
    UserDto getDtoById(Long id);
    User getEntityByEmail(String email);
    UserDto getDtoByEmail(String email);

    List<UserDto> getAllDtos();

    UserDto save(User user);
    UserDto save(UserDtoRequest request);
    UserDto update(UserDtoRequest request);
}
