package org.example.autoschool.utils.mapper;

import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.AvailableSlotDtoRequest;
import org.example.autoschool.dto.request.DriverLicenceDtoRequest;
import org.example.autoschool.dto.response.AvailableSlotDto;
import org.example.autoschool.dto.response.DriverLicenceDto;
import org.example.autoschool.entity.AvailableSlot;
import org.example.autoschool.entity.DriverLicence;
import org.example.autoschool.enums.Category;
import org.example.autoschool.service.ExamDayService;
import org.example.autoschool.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DriverLicenceMapper {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    public DriverLicenceDto toDto(DriverLicence licence) {
        return new DriverLicenceDto().toBuilder()
                .id(licence.getId())
                .licenceNumber(licence.getLicenceNumber())
                .category(licence.getCategory())
                .status(licence.getStatus())
                .issueDate(licence.getIssueDate())
                .expiryDate(licence.getExpiryDate())
                .driver(studentMapper.toDto(licence.getDriver()))
                .build();
    }

    public List<DriverLicenceDto> toDtoList(List<DriverLicence> licences) {
        return licences.stream().map(this::toDto).collect(Collectors.toList());
    }

    public DriverLicence toEntity(DriverLicenceDtoRequest licenceDtoRequest) {
        return new DriverLicence().toBuilder()
                .id(licenceDtoRequest.getId())
                .licenceNumber(licenceDtoRequest.getLicenceNumber())
                .category(Category.valueOf(licenceDtoRequest.getCategory()))
                .status(licenceDtoRequest.getStatus())
                .issueDate(licenceDtoRequest.getIssueDate())
                .expiryDate(licenceDtoRequest.getExpiryDate())
                .driver(studentService.getEntityById(licenceDtoRequest.getStudentId()))
                .build();
    }
}
