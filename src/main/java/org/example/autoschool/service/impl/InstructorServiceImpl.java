package org.example.autoschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.InstructorDtoRequest;
import org.example.autoschool.dto.response.InstructorDto;
import org.example.autoschool.entity.Instructor;
import org.example.autoschool.service.InstructorService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    @Override
    public Instructor getEntityById(Long id) {
        return null;
    }

    @Override
    public InstructorDto getDtoById(Long id) {
        return null;
    }

    @Override
    public InstructorDto getDtoByEmail(String email) {
        return null;
    }

    @Override
    public Instructor getDtoByUserID(Long id) {
        return null;
    }

    @Override
    public InstructorDto save(InstructorDtoRequest request) {
        return null;
    }

    @Override
    public InstructorDto update(InstructorDtoRequest request) {
        return null;
    }
}
