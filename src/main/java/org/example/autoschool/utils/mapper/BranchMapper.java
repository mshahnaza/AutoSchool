package org.example.autoschool.utils.mapper;

import lombok.NoArgsConstructor;
import org.example.autoschool.dto.request.BranchDtoRequest;
import org.example.autoschool.dto.response.BranchDto;
import org.example.autoschool.entity.Branch;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class BranchMapper {

    public BranchDto toDto(Branch branch) {
        return new BranchDto().toBuilder()
                .id(branch.getId())
                .name(branch.getName())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .build();
    }

    public List<BranchDto> toDtoList(List<Branch> branches) {
        return branches.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Branch toEntity(BranchDtoRequest branchDtoRequest) {
        return new Branch().toBuilder()
                .id(branchDtoRequest.getId())
                .name(branchDtoRequest.getName())
                .address(branchDtoRequest.getAddress())
                .phone(branchDtoRequest.getPhone())
                .build();
    }
}
