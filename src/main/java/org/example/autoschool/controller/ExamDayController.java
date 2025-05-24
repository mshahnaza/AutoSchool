package org.example.autoschool.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.ExamDayDtoRequest;
import org.example.autoschool.dto.response.ExamDayDto;
import org.example.autoschool.service.ExamDayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exam-day")
@RequiredArgsConstructor
@Tag(name = "Exam Day Management", description = "Operations related to managing exam day entities in the autoSchool system")
public class ExamDayController {

    private final ExamDayService examDayService;

    @Operation(
            summary = "Retrieve exam day by ID",
            description = "Retrieve an exam day by its unique ID.",
            tags = {"Exam Day Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exam day successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Exam day not found")
    })
    @GetMapping("/by-id/{id}")
    public ResponseEntity<ExamDayDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(examDayService.getDtoById(id));
    }

    @Operation(
            summary = "Retrieve all exam days",
            description = "Retrieve a list of all exam days.",
            tags = {"Exam Day Management"}
    )
    @ApiResponse(responseCode = "200", description = "List of exam days successfully retrieved")
    @GetMapping("/get-all")
    public ResponseEntity<List<ExamDayDto>> getAll() {
        return ResponseEntity.ok(examDayService.getAllDtos());
    }

    @Operation(
            summary = "Retrieve exam days by date",
            description = "Retrieve exam days scheduled on a specific date (format: yyyy-MM-dd).",
            tags = {"Exam Day Management"}
    )
    @ApiResponse(responseCode = "200", description = "Exam days for the specified date successfully retrieved")
    @GetMapping("/by-date")
    public ResponseEntity<List<ExamDayDto>> getByDate(@RequestParam String date) {
        return ResponseEntity.ok(examDayService.getDtoByDate(date));
    }

    @Operation(
            summary = "Retrieve exam days by category",
            description = "Retrieve exam days filtered by category.",
            tags = {"Exam Day Management"}
    )
    @ApiResponse(responseCode = "200", description = "Exam days for the specified category successfully retrieved")
    @GetMapping("/by-category")
    public ResponseEntity<List<ExamDayDto>> getByCategory(@RequestParam String category) {
        return ResponseEntity.ok(examDayService.getDtoByCategory(category));
    }

    @Operation(
            summary = "Retrieve exam days by exam type",
            description = "Retrieve exam days filtered by exam type.",
            tags = {"Exam Day Management"}
    )
    @ApiResponse(responseCode = "200", description = "Exam days for the specified exam type successfully retrieved")
    @GetMapping("/by-exam-type")
    public ResponseEntity<List<ExamDayDto>> getByExamType(@RequestParam String examType) {
        return ResponseEntity.ok(examDayService.getDtoByExamType(examType));
    }

    @Operation(
            summary = "Retrieve exam days by branch ID",
            description = "Retrieve exam days associated with a specific branch ID.",
            tags = {"Exam Day Management"}
    )
    @ApiResponse(responseCode = "200", description = "Exam days for the specified branch successfully retrieved")
    @GetMapping("/by-branch")
    public ResponseEntity<List<ExamDayDto>> getByBranchId(@RequestParam Long id) {
        return ResponseEntity.ok(examDayService.getDtoByBranchId(id));
    }

    @Operation(
            summary = "Retrieve exam days by date, exam type, and category",
            description = "Retrieve exam days filtered by date, exam type, and category together.",
            tags = {"Exam Day Management"}
    )
    @ApiResponse(responseCode = "200", description = "Filtered exam days successfully retrieved")
    @GetMapping("/filter")
    public ResponseEntity<List<ExamDayDto>> getByDateExamTypeCategory(
            @RequestParam String date,
            @RequestParam String examType,
            @RequestParam String category) {
        return ResponseEntity.ok(examDayService.getByDateAndExamTypeAndCategory(date, examType, category));
    }

    @Operation(
            summary = "Retrieve exam days by date and exam type",
            description = "Retrieve exam days filtered by date and exam type.",
            tags = {"Exam Day Management"}
    )
    @ApiResponse(responseCode = "200", description = "Filtered exam days successfully retrieved")
    @GetMapping("/filter-by-date-examType")
    public ResponseEntity<List<ExamDayDto>> getByDateAndExamType(
            @RequestParam String date,
            @RequestParam String examType) {
        return ResponseEntity.ok(examDayService.getByDateAndExamType(date, examType));
    }

    @Operation(
            summary = "Retrieve exam days by date and category",
            description = "Retrieve exam days filtered by date and category.",
            tags = {"Exam Day Management"}
    )
    @ApiResponse(responseCode = "200", description = "Filtered exam days successfully retrieved")
    @GetMapping("/filter-by-date-category")
    public ResponseEntity<List<ExamDayDto>> getByDateAndCategory(
            @RequestParam String date,
            @RequestParam String category) {
        return ResponseEntity.ok(examDayService.getByDateAndCategory(date, category));
    }

    @Operation(
            summary = "Retrieve exam days by exam type and category",
            description = "Retrieve exam days filtered by exam type and category.",
            tags = {"Exam Day Management"}
    )
    @ApiResponse(responseCode = "200", description = "Filtered exam days successfully retrieved")
    @GetMapping("/filter-by-examType-category")
    public ResponseEntity<List<ExamDayDto>> getByExamTypeAndCategory(
            @RequestParam String examType,
            @RequestParam String category) {
        return ResponseEntity.ok(examDayService.getByExamTypeAndCategory(examType, category));
    }

    @Operation(
            summary = "Create a new exam day",
            description = "Create a new exam day with the provided details.",
            tags = {"Exam Day Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Exam day successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request data or exam day already exists")
    })
    @PostMapping("/create")
    public ResponseEntity<ExamDayDto> create(@RequestBody ExamDayDtoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(examDayService.save(request));
    }

    @Operation(
            summary = "Update an existing exam day",
            description = "Update an existing exam day with the provided details.",
            tags = {"Exam Day Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exam day successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request data or exam day already exists"),
            @ApiResponse(responseCode = "404", description = "Exam day not found")
    })
    @PutMapping("/update")
    public ResponseEntity<ExamDayDto> update(@RequestBody ExamDayDtoRequest request) {
        return ResponseEntity.ok(examDayService.update(request));
    }
}

