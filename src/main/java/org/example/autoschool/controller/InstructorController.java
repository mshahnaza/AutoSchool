package org.example.autoschool.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.InstructorDtoRequest;
import org.example.autoschool.dto.response.InstructorDto;
import org.example.autoschool.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/instructor")
@RequiredArgsConstructor
@Tag(name = "Instructor Management", description = "Operations related to managing instructor entities in the autoschool system")
public class InstructorController {

    private final InstructorService instructorService;

    @Operation(
            summary = "Get instructor by ID",
            description = "Retrieve an instructor by their unique ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Instructor not found")
    })
    @GetMapping("/by-id/{id}")
    public ResponseEntity<InstructorDto> getById(
            @Parameter(description = "ID of the instructor") @PathVariable Long id) {
        return ResponseEntity.ok(instructorService.getDtoById(id));
    }

    @Operation(
            summary = "Get instructor by email",
            description = "Retrieve an instructor using the email address linked to their user account"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Instructor not found")
    })
    @GetMapping("/by-email")
    public ResponseEntity<InstructorDto> getByEmail(
            @Parameter(description = "Email of the instructor user") @RequestParam String email) {
        return ResponseEntity.ok(instructorService.getDtoByEmail(email));
    }

    @Operation(
            summary = "Get instructor by user ID",
            description = "Retrieve an instructor based on the ID of the linked user entity"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Instructor not found")
    })
    @GetMapping("/by-user-id")
    public ResponseEntity<InstructorDto> getByUserId(
            @Parameter(description = "User ID associated with the instructor") @RequestParam Long userId) {
        return ResponseEntity.ok(instructorService.getDtoById(instructorService.getDtoByUserID(userId).getId()));
    }

    @Operation(
            summary = "Get instructor by exam ID",
            description = "Retrieve an instructor assigned to a specific exam"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Instructor not found")
    })
    @GetMapping("/by-exam-id")
    public ResponseEntity<InstructorDto> getByExamId(
            @Parameter(description = "Exam ID to find the instructor for") @RequestParam Long examId) {
        return ResponseEntity.ok(instructorService.getDtoByExamId(examId));
    }

    @Operation(
            summary = "Get all instructors",
            description = "Retrieve a list of all instructors"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of instructors retrieved successfully")
    })
    @GetMapping("/get-all")
    public ResponseEntity<List<InstructorDto>> getAll() {
        return ResponseEntity.ok(instructorService.getAllDtos());
    }

    @Operation(
            summary = "Create a new instructor",
            description = "Register a new instructor in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/create")
    public ResponseEntity<InstructorDto> create(
            @Parameter(description = "Instructor data for creation") @RequestBody InstructorDtoRequest request) {
        return ResponseEntity.ok(instructorService.save(request));
    }

    @Operation(
            summary = "Update an instructor",
            description = "Update existing instructor information"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor updated successfully"),
            @ApiResponse(responseCode = "404", description = "Instructor not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/update")
    public ResponseEntity<InstructorDto> update(
            @Parameter(description = "Instructor data for update") @RequestBody InstructorDtoRequest request) {
        return ResponseEntity.ok(instructorService.update(request));
    }
}
