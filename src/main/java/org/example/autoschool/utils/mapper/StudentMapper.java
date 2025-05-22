package org.example.autoschool.utils.mapper;

import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.StudentDtoRequest;
import org.example.autoschool.dto.response.StudentDto;
import org.example.autoschool.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentMapper {
    private final UserMapper userMapper;

    public StudentDto toDto(Student student) {
        return new StudentDto().toBuilder()
                .id(student.getId())
                .passportId(student.getPassportId())
                .user(userMapper.toDto(student.getStudentUser()))
                .build();
    }

    public List<StudentDto> toDtoList(List<Student> students) {
        return students.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Student toEntity(StudentDtoRequest studentDtoRequest) {
        return new Student().toBuilder()
                .id(studentDtoRequest.getId())
                .passportId(studentDtoRequest.getPassportId())
                .studentUser(userMapper.toEntity(studentDtoRequest.getUser()))
                .build();
    }
}
