package org.example.autoschool.service;

import org.example.autoschool.dto.request.DriverLicenceDtoRequest;
import org.example.autoschool.dto.response.DriverLicenceDto;
import org.example.autoschool.entity.DriverLicence;

import java.util.List;

public interface DriverLicenceService {
    DriverLicence getEntityById(Long id);
    DriverLicenceDto getDtoById(Long id);

    List<DriverLicenceDto> getAllDtos();
    List<DriverLicenceDto> getByStudentName(String studentName);
    List<DriverLicenceDto> getDtoByStudentId(Long id);
    List<DriverLicenceDto> getDtoByDate(String date);

    List<DriverLicenceDto> getActiveDtoByStudentId(Long id);
    List<DriverLicenceDto> getActiveDtoByStudentName(String studentName);

    DriverLicenceDto save(DriverLicenceDtoRequest request);
    DriverLicenceDto update(DriverLicenceDtoRequest request);
}
