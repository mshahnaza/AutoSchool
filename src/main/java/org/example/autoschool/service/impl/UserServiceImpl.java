package org.example.autoschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.UserDtoRequest;
import org.example.autoschool.dto.response.UserDto;
import org.example.autoschool.entity.User;
import org.example.autoschool.repository.UserRepository;
import org.example.autoschool.service.UserService;
import org.example.autoschool.utils.exception.AlreadyExistException;
import org.example.autoschool.utils.exception.ObjectNotFoundException;
import org.example.autoschool.utils.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private void checkEmailExist(String email) {
        if (!userRepository.existsByEmail(email))
            throw new AlreadyExistException("User", "email", email);
    }

    @Override
    public User getEntityById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User with id: " + id + " not found"));
    }

    @Override
    public UserDto getDtoById(Long id) {
        return userMapper.toDto(getEntityById(id));
    }

    @Override
    public UserDto getDtoByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email));
    }

    @Override
    public UserDto save(User user) {
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto save(UserDtoRequest request) {
        checkEmailExist(request.getEmail());
        User user = userRepository.save(userMapper.toEntity(request));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto update(UserDtoRequest request) {
        User user = getEntityById(request.getId());
        User newUser = userMapper.toEntity(request);

        user.setEmail(newUser.getEmail());
        user.setName(newUser.getName());
        user.setPassword(newUser.getPassword());
        user.setDateOfBirth(newUser.getDateOfBirth());
        user.setPhone(newUser.getPhone());
        user.setRole(newUser.getRole());

        return userMapper.toDto(userRepository.save(user));
    }
}
