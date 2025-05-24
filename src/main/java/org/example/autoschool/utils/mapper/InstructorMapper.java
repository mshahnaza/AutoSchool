package org.example.autoschool.utils.mapper;

import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.InstructorDtoRequest;
import org.example.autoschool.dto.response.InstructorDto;
import org.example.autoschool.entity.Instructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InstructorMapper {
    private final UserMapper userMapper;

    public InstructorDto toDto(Instructor instructor) {
        return new InstructorDto().toBuilder()
                .id(instructor.getId())
                .licenseNumber(instructor.getLicenseNumber())
                .user(userMapper.toDto(instructor.getInstructorUser()))
                .build();
    }

    public List<InstructorDto> toDtoList(List<Instructor> instructors) {
        return instructors.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Instructor toEntity(InstructorDtoRequest instructorDtoRequest) {
        return new Instructor().toBuilder()
                .id(instructorDtoRequest.getId())
                .licenseNumber(instructorDtoRequest.getLicenseNumber())
                .instructorUser(userMapper.toEntity(instructorDtoRequest.getUser()))
                .build();
    }
}
