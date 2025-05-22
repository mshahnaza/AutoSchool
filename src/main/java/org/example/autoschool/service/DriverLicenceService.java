package org.example.autoschool.service;

import org.example.autoschool.dto.request.DriverLicenceDtoRequest;
import org.example.autoschool.dto.response.DriverLicenceDto;
import org.example.autoschool.entity.DriverLicence;

public interface DriverLicenceService {
    DriverLicence getEntityById(Long id);
    DriverLicenceDto getDtoById(Long id);

    DriverLicenceDto save(DriverLicenceDtoRequest request);
    DriverLicenceDto update(DriverLicenceDtoRequest request);
}
