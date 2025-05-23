package org.example.autoschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.InstructorDtoRequest;
import org.example.autoschool.dto.response.InstructorDto;
import org.example.autoschool.entity.Instructor;
import org.example.autoschool.entity.Student;
import org.example.autoschool.repository.InstructorRepository;
import org.example.autoschool.service.InstructorService;
import org.example.autoschool.service.UserService;
import org.example.autoschool.utils.exception.ObjectNotFoundException;
import org.example.autoschool.utils.mapper.InstructorMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;
    private final UserService userService;

    @Override
    public Instructor getEntityById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Instructor with id: " + id + " not found"));
    }

    @Override
    public InstructorDto getDtoById(Long id) {
        return instructorMapper.toDto(getEntityById(id));
    }

    @Override
    public InstructorDto getDtoByEmail(String email) {
        return instructorMapper.toDto(
                instructorRepository.findByUserEmail(email)
                        .orElseThrow(() -> new ObjectNotFoundException("Student with email: " + email + " not found")));
    }

    @Override
    public Instructor getDtoByUserID(Long id) {
        return instructorRepository.findByUserId(id)
                .orElseThrow(() -> new ObjectNotFoundException("Student with user id: " + id + " not found"));
    }

    @Override
    public InstructorDto getDtoByExamId(Long id) {
        return instructorMapper.toDto(
                instructorRepository.findInstructorByExamId(id));
    }

    @Override
    public List<InstructorDto> getAllDtos() {
        return instructorMapper.toDtoList(instructorRepository.findAll());
    }

    @Override
    public Instructor save(Instructor request) {
        return instructorRepository.save(request);
    }

    @Override
    public InstructorDto save(InstructorDtoRequest request) {
        Instructor instructor = instructorMapper.toEntity(request);
        userService.save(instructor.getInstructorUser());
        return instructorMapper.toDto(instructorRepository.save(instructor));
    }

    @Override
    public InstructorDto update(InstructorDtoRequest request) {
        Instructor instructor = instructorMapper.toEntity(request);
        Instructor newInstructor = getEntityById(instructor.getId());

        newInstructor.setLicenseNumber(instructor.getLicenseNumber());
        newInstructor.setInstructorUser(instructor.getInstructorUser());

        return instructorMapper.toDto(instructorRepository.save(newInstructor));
    }
}
