package org.example.autoschool.service;

import org.example.autoschool.dto.request.StudentDtoRequest;
import org.example.autoschool.dto.response.StudentDto;
import org.example.autoschool.entity.Student;

import java.util.List;

public interface StudentService {
    Student getEntityById(Long id);
    StudentDto getDtoById(Long id);
    StudentDto getDtoByEmail(String email);
    Student getDtoByUserID(Long id);

    List<StudentDto> getAllDtos();

    Student save(Student request);
    StudentDto save(StudentDtoRequest request);
    StudentDto update(StudentDtoRequest request);
}
