package org.example.autoschool.service;

import org.example.autoschool.dto.request.InstructorDtoRequest;
import org.example.autoschool.dto.response.InstructorDto;
import org.example.autoschool.entity.Instructor;

public interface InstructorService {
    Instructor getEntityById(Long id);
    InstructorDto getDtoById(Long id);
    InstructorDto getDtoByEmail(String email);
    Instructor getDtoByUserID(Long id);

    InstructorDto save(InstructorDtoRequest request);
    InstructorDto update(InstructorDtoRequest request);
}
