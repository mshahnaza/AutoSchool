package org.example.autoschool.service;

import org.example.autoschool.dto.request.AvailableSlotDtoRequest;
import org.example.autoschool.dto.response.AvailableSlotDto;
import org.example.autoschool.entity.AvailableSlot;

public interface AvailableSlotService {
    AvailableSlot getEntityById(Long id);
    AvailableSlotDto getDtoById(Long id);

    AvailableSlotDto save(AvailableSlotDtoRequest request);
    AvailableSlotDto update(AvailableSlotDtoRequest request);
}
