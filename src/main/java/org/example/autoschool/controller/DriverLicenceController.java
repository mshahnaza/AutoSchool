package org.example.autoschool.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.DriverLicenceDtoRequest;
import org.example.autoschool.dto.response.DriverLicenceDto;
import org.example.autoschool.service.DriverLicenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/driver-licence")
@RequiredArgsConstructor
@Tag(name = "Driver Licence Management", description = "Operations related to managing driver licence entities in the autoSchool system")
public class DriverLicenceController {

    private final DriverLicenceService driverLicenceService;

    @Operation(summary = "Get Driver Licence by ID",
            description = "Retrieve a driver licence by its unique ID",
            tags = {"DriverLicence Management"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Driver licence found"),
            @ApiResponse(responseCode = "404", description = "Driver licence not found")
    })
    @GetMapping("/by-id/{id}")
    public ResponseEntity<DriverLicenceDto> getById(@PathVariable Long id) {
        DriverLicenceDto dto = driverLicenceService.getDtoById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Get all Driver Licences",
            description = "Retrieve a list of all driver licences",
            tags = {"DriverLicence Management"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of driver licences retrieved")
    })
    @GetMapping("get-all")
    public ResponseEntity<List<DriverLicenceDto>> getAll() {
        List<DriverLicenceDto> list = driverLicenceService.getAllDtos();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get Driver Licences by Student Name",
            description = "Retrieve driver licences by student name",
            tags = {"DriverLicence Management"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of driver licences for the student")
    })
    @GetMapping("/student-name/{studentName}")
    public ResponseEntity<List<DriverLicenceDto>> getByStudentName(@PathVariable String studentName) {
        List<DriverLicenceDto> list = driverLicenceService.getByStudentName(studentName);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get Driver Licences by Student ID",
            description = "Retrieve driver licences by student ID",
            tags = {"DriverLicence Management"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of driver licences for the student")
    })
    @GetMapping("/student-id/{studentId}")
    public ResponseEntity<List<DriverLicenceDto>> getByStudentId(@PathVariable("studentId") Long studentId) {
        List<DriverLicenceDto> list = driverLicenceService.getDtoByStudentId(studentId);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get Driver Licences by Date",
            description = "Retrieve driver licences by issue or expiry date (format: yyyy-MM-dd)",
            tags = {"DriverLicence Management"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of driver licences for the specified date"),
            @ApiResponse(responseCode = "400", description = "Invalid date format")
    })
    @GetMapping("/date/{date}")
    public ResponseEntity<List<DriverLicenceDto>> getByDate(@PathVariable String date) {
        List<DriverLicenceDto> list = driverLicenceService.getDtoByDate(date);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get Active Driver Licences by Student ID",
            description = "Retrieve active driver licences for a student by ID",
            tags = {"DriverLicence Management"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of active driver licences for the student")
    })
    @GetMapping("/active/student-id/{studentId}")
    public ResponseEntity<List<DriverLicenceDto>> getActiveByStudentId(@PathVariable("studentId") Long studentId) {
        List<DriverLicenceDto> list = driverLicenceService.getActiveDtoByStudentId(studentId);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get Active Driver Licences by Student Name",
            description = "Retrieve active driver licences for a student by name",
            tags = {"DriverLicence Management"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of active driver licences for the student")
    })
    @GetMapping("/active/student-name/{studentName}")
    public ResponseEntity<List<DriverLicenceDto>> getActiveByStudentName(@PathVariable String studentName) {
        List<DriverLicenceDto> list = driverLicenceService.getActiveDtoByStudentName(studentName);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Create new Driver Licence",
            description = "Save a new driver licence record",
            tags = {"DriverLicence Management"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Driver licence successfully created")
    })
    @PostMapping("/create")
    public ResponseEntity<DriverLicenceDto> save(@RequestBody DriverLicenceDtoRequest request) {
        DriverLicenceDto saved = driverLicenceService.save(request);
        return ResponseEntity.status(201).body(saved);
    }

    @Operation(summary = "Update existing Driver Licence",
            description = "Update an existing driver licence record",
            tags = {"DriverLicence Management"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Driver licence successfully updated"),
            @ApiResponse(responseCode = "404", description = "Driver licence to update not found")
    })
    @PutMapping("/update")
    public ResponseEntity<DriverLicenceDto> update(@RequestBody DriverLicenceDtoRequest request) {
        DriverLicenceDto updated = driverLicenceService.update(request);
        return ResponseEntity.ok(updated);
    }
}
