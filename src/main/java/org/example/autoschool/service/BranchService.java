package org.example.autoschool.service;

import org.example.autoschool.dto.request.BranchDtoRequest;
import org.example.autoschool.dto.response.BranchDto;
import org.example.autoschool.entity.Branch;

import java.util.List;

public interface BranchService {
    Branch getEntityById(Long id);
    BranchDto getDtoById(Long id);

    List<BranchDto> getAllDtos();

    BranchDto save(BranchDtoRequest branchDtoRequest);
    BranchDto update(BranchDtoRequest branchDtoRequest);
}
