package org.example.autoschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.StudentDtoRequest;
import org.example.autoschool.dto.response.StudentDto;
import org.example.autoschool.entity.Student;
import org.example.autoschool.repository.StudentRepository;
import org.example.autoschool.service.StudentService;
import org.example.autoschool.service.UserService;
import org.example.autoschool.utils.exception.ObjectNotFoundException;
import org.example.autoschool.utils.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final UserService userService;

    @Override
    public Student getEntityById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Student with id: " + id + " not found"));
    }

    @Override
    public StudentDto getDtoById(Long id) {
        return studentMapper.toDto(getEntityById(id));
    }

    @Override
    public StudentDto getDtoByEmail(String email) {
        return studentMapper.toDto(
                studentRepository.findByUserEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("Student with email: " + email + " not found")));
    }

    @Override
    public Student getDtoByUserID(Long id) {
        return studentRepository.findByUserId(id)
                .orElseThrow(() -> new ObjectNotFoundException("Student with user id: " + id + " not found"));
    }

    @Override
    public List<StudentDto> getAllDtos() {
        return studentMapper.toDtoList(studentRepository.findAll());
    }

    @Override
    public Student save(Student request) {
        return studentRepository.save(request);
    }

    @Override
    public StudentDto save(StudentDtoRequest request) {
        Student student = studentMapper.toEntity(request);
        userService.save(student.getStudentUser());
        return studentMapper.toDto(studentRepository.save(student));
    }

    @Override
    public StudentDto update(StudentDtoRequest request) {
        Student student = studentMapper.toEntity(request);
        Student newStudent = getEntityById(student.getId());

        newStudent.setDriverLicences(student.getDriverLicences());
        newStudent.setStudentUser(newStudent.getStudentUser());

        return studentMapper.toDto(studentRepository.save(newStudent));
    }
}
