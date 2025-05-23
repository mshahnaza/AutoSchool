package org.example.autoschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.BranchDtoRequest;
import org.example.autoschool.dto.response.BranchDto;
import org.example.autoschool.entity.Branch;
import org.example.autoschool.repository.BranchRepository;
import org.example.autoschool.service.BranchService;
import org.example.autoschool.utils.exception.ObjectNotFoundException;
import org.example.autoschool.utils.mapper.BranchMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    @Override
    public Branch getEntityById(Long id) {
        return branchRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Branch with id: " + id + "not found"));
    }

    @Override
    public BranchDto getDtoById(Long id) {
        return branchMapper.toDto(getEntityById(id));
    }

    @Override
    public List<BranchDto> getAllDtos() {
        return branchMapper.toDtoList(branchRepository.findAll());
    }

    @Override
    public BranchDto save(BranchDtoRequest branchDtoRequest) {
        Branch branch = branchMapper.toEntity(branchDtoRequest);
        return branchMapper.toDto(branchRepository.save(branch));
    }

    @Override
    public BranchDto update(BranchDtoRequest branchDtoRequest) {
        Branch branch = branchMapper.toEntity(branchDtoRequest);
        Branch newBranch = getEntityById(branch.getId());

        newBranch.setName(branch.getName());
        newBranch.setAddress(branch.getAddress());
        newBranch.setAddress(branch.getAddress());

        return branchMapper.toDto(branchRepository.save(newBranch));
    }
}
