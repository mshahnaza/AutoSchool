package org.example.autoschool.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.BranchDtoRequest;
import org.example.autoschool.dto.response.BranchDto;
import org.example.autoschool.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/branch")
@RequiredArgsConstructor
@Tag(name = "Branch Management", description = "Operations related to managing branch entities in the autoschool system")
public class BranchController {

    private final BranchService branchService;

    @Operation(
            summary = "Retrieve branch by ID",
            description = "This operation allows clients to retrieve a branch using its unique ID.",
            tags = {"Branch Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Branch successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Branch not found")
    })
    @GetMapping("/by-id/{id}")
    public ResponseEntity<BranchDto> getBranchById(@PathVariable Long id) {
        BranchDto branchDto = branchService.getDtoById(id);
        return ResponseEntity.ok(branchDto);
    }

    @Operation(
            summary = "Retrieve all branches",
            description = "This operation retrieves a list of all branches.",
            tags = {"Branch Management"}
    )
    @ApiResponse(responseCode = "200", description = "List of branches successfully retrieved")
    @GetMapping("/get-all")
    public ResponseEntity<List<BranchDto>> getAllBranches() {
        List<BranchDto> branches = branchService.getAllDtos();
        return ResponseEntity.ok(branches);
    }

    @Operation(
            summary = "Create a new branch",
            description = "This operation creates a new branch with the provided data.",
            tags = {"Branch Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Branch successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping("/create")
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDtoRequest branchDtoRequest) {
        BranchDto createdBranch = branchService.save(branchDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBranch);
    }

    @Operation(
            summary = "Update an existing branch",
            description = "This operation updates an existing branch with the provided data.",
            tags = {"Branch Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Branch successfully updated"),
            @ApiResponse(responseCode = "404", description = "Branch not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PutMapping("/update")
    public ResponseEntity<BranchDto> updateBranch(@RequestBody BranchDtoRequest branchDtoRequest) {
        BranchDto updatedBranch = branchService.update(branchDtoRequest);
        return ResponseEntity.ok(updatedBranch);
    }
}
