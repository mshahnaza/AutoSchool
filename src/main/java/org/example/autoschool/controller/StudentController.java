package org.example.autoschool.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.StudentDtoRequest;
import org.example.autoschool.dto.response.StudentDto;
import org.example.autoschool.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
@Tag(name = "Student Management", description = "Operations related to managing student entities in the autoSchool system")
public class StudentController {

    private final StudentService studentService;

    @Operation(
            summary = "Get student by ID",
            description = "Retrieve a student by their unique ID",
            tags = {"Student Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/by-id/{id}")
    public ResponseEntity<StudentDto> getById(
            @Parameter(description = "ID of the student to retrieve") @PathVariable Long id) {
        return ResponseEntity.ok(studentService.getDtoById(id));
    }

    @Operation(
            summary = "Get student by email",
            description = "Retrieve a student using the email of the linked user account",
            tags = {"Student Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/by-email")
    public ResponseEntity<StudentDto> getByEmail(
            @Parameter(description = "Email of the student user") @RequestParam String email) {
        return ResponseEntity.ok(studentService.getDtoByEmail(email));
    }

    @Operation(
            summary = "Get student by User ID",
            description = "Retrieve a student using the ID of the linked user entity",
            tags = {"Student Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/by-user-id")
    public ResponseEntity<StudentDto> getByUserId(
            @Parameter(description = "User ID linked to the student") @RequestParam Long userId) {
        return ResponseEntity.ok(studentService.getDtoById(studentService.getDtoByUserID(userId).getId()));
    }

    @Operation(
            summary = "Get all students",
            description = "Retrieve a list of all students in the system",
            tags = {"Student Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of students retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No students found")
    })
    @GetMapping("/get-all")
    public ResponseEntity<List<StudentDto>> getAll() {
        return ResponseEntity.ok(studentService.getAllDtos());
    }

    @Operation(
            summary = "Create a new student",
            description = "Create and register a new student in the system",
            tags = {"Student Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/create")
    public ResponseEntity<StudentDto> create(
            @Parameter(description = "Student data for creation") @RequestBody StudentDtoRequest request) {
        return ResponseEntity.ok(studentService.save(request));
    }

    @Operation(
            summary = "Update an existing student",
            description = "Update student details, such as driver's license info",
            tags = {"Student Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student successfully updated"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "400", description = "Invalid update data")
    })
    @PutMapping("/update")
    public ResponseEntity<StudentDto> update(
            @Parameter(description = "Updated student data") @RequestBody StudentDtoRequest request) {
        return ResponseEntity.ok(studentService.update(request));
    }
}
