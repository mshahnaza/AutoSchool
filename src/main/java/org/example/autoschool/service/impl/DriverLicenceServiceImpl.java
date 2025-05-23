package org.example.autoschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.DriverLicenceDtoRequest;
import org.example.autoschool.dto.response.DriverLicenceDto;
import org.example.autoschool.entity.DriverLicence;
import org.example.autoschool.repository.DriverLicenceRepository;
import org.example.autoschool.service.DriverLicenceService;
import org.example.autoschool.utils.exception.ObjectNotFoundException;
import org.example.autoschool.utils.mapper.DriverLicenceMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverLicenceServiceImpl implements DriverLicenceService {
    private final DriverLicenceRepository driverLicenceRepository;
    private final DriverLicenceMapper driverLicenceMapper;

    @Override
    public DriverLicence getEntityById(Long id) {
        return driverLicenceRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Driver licence with id: " + id + " not found"));
    }

    @Override
    public DriverLicenceDto getDtoById(Long id) {
        return driverLicenceMapper.toDto(getEntityById(id));
    }

    @Override
    public List<DriverLicenceDto> getAllDtos() {
        return driverLicenceMapper.toDtoList(driverLicenceRepository.findAll());
    }

    @Override
    public List<DriverLicenceDto> getByStudentName(String studentName) {
        return driverLicenceMapper.toDtoList(driverLicenceRepository.findByStudentName(studentName));
    }

    @Override
    public List<DriverLicenceDto> getDtoByStudentId(Long id) {
        return driverLicenceMapper.toDtoList(driverLicenceRepository.findDtoByStudentId(id));
    }

    @Override
    public List<DriverLicenceDto> getDtoByDate(String date) {
        return driverLicenceMapper.toDtoList(driverLicenceRepository.findDtoByDate(LocalDate.parse(date)));
    }

    @Override
    public List<DriverLicenceDto> getActiveDtoByStudentId(Long id) {
        return driverLicenceMapper.toDtoList(driverLicenceRepository.findActiveDtoByStudentId(id));
    }

    @Override
    public List<DriverLicenceDto> getActiveDtoByStudentName(String studentName) {
        return driverLicenceMapper.toDtoList(driverLicenceRepository.findActiveDtoByStudentName(studentName));
    }

    @Override
    public DriverLicenceDto save(DriverLicenceDtoRequest request) {
        DriverLicence driverLicence = driverLicenceMapper.toEntity(request);
        return driverLicenceMapper.toDto(driverLicenceRepository.save(driverLicence));
    }

    @Override
    public DriverLicenceDto update(DriverLicenceDtoRequest request) {
        DriverLicence driverLicence = driverLicenceMapper.toEntity(request);
        DriverLicence newDriverLicence = getEntityById(driverLicence.getId());

        newDriverLicence.setDriver(driverLicence.getDriver());
        newDriverLicence.setLicenceNumber(driverLicence.getLicenceNumber());
        newDriverLicence.setCategory(driverLicence.getCategory());
        newDriverLicence.setStatus(driverLicence.getStatus());
        newDriverLicence.setIssueDate(driverLicence.getIssueDate());
        newDriverLicence.setExpiryDate(driverLicence.getExpiryDate());

        return driverLicenceMapper.toDto(driverLicenceRepository.save(newDriverLicence));
    }
}
