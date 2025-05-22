package org.example.autoschool.service;

import org.example.autoschool.dto.request.StudentDtoRequest;
import org.example.autoschool.dto.response.StudentDto;
import org.example.autoschool.entity.Student;

public interface StudentService {
    Student getEntityById(Long id);
    StudentDto getDtoById(Long id);
    StudentDto getDtoByEmail(String email);
    Student getDtoByUserID(Long id);

    StudentDto save(StudentDtoRequest request);
    StudentDto update(StudentDtoRequest request);
}
