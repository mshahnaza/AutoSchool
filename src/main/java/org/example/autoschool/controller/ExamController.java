package org.example.autoschool.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.ExamDtoRequest;
import org.example.autoschool.dto.response.ExamDto;
import org.example.autoschool.service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exam")
@RequiredArgsConstructor
@Tag(name = "Exam Management", description = "Operations related to managing exam entities in the autoSchool system")
public class ExamController {

    private final ExamService examService;

    @Operation(
            summary = "Get Exam by ID",
            description = "Retrieve an exam by its unique ID.",
            tags = {"Exam Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exam found and returned"),
            @ApiResponse(responseCode = "404", description = "Exam not found")
    })
    @GetMapping("/by-id/{id}")
    public ResponseEntity<ExamDto> getById(@PathVariable Long id) {
        ExamDto dto = examService.getDtoById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Get all Exams",
            description = "Retrieve a list of all exams.",
            tags = {"Exam Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of exams returned successfully")
    })
    @GetMapping("/get-all")
    public ResponseEntity<List<ExamDto>> getAll() {
        List<ExamDto> list = examService.getAllDtos();
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Get Exams by Student ID",
            description = "Retrieve all exams for a specific student by their ID.",
            tags = {"Exam Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of exams for the student returned successfully")
    })
    @GetMapping("/student-id/{studentId}")
    public ResponseEntity<List<ExamDto>> getByStudentId(@PathVariable Long studentId) {
        List<ExamDto> list = examService.getDtosByStudentId(studentId);
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Get Exams by Slot ID",
            description = "Retrieve all exams scheduled for a specific slot by its ID.",
            tags = {"Exam Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of exams for the slot returned successfully")
    })
    @GetMapping("/slot-id/{slotId}")
    public ResponseEntity<List<ExamDto>> getBySlotId(@PathVariable Long slotId) {
        List<ExamDto> list = examService.getDtosBySlotId(slotId);
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Get Exams by Instructor ID",
            description = "Retrieve all exams conducted by a specific instructor by their ID.",
            tags = {"Exam Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of exams for the instructor returned successfully")
    })
    @GetMapping("/instructor-id/{instructorId}")
    public ResponseEntity<List<ExamDto>> getByInstructorId(@PathVariable Long instructorId) {
        List<ExamDto> list = examService.getDtosByInstructorId(instructorId);
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Get Exams by Category",
            description = "Retrieve all exams filtered by driving license category.",
            tags = {"Exam Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of exams filtered by category returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid category value")
    })
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ExamDto>> getByCategory(@PathVariable String category) {
        List<ExamDto> list = examService.getDtosByCategory(category);
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Get Exams by Exam Type",
            description = "Retrieve all exams filtered by exam type (e.g., THEORETICAL, PRACTICAL).",
            tags = {"Exam Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of exams filtered by exam type returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid exam type value")
    })
    @GetMapping("/exam-type/{examType}")
    public ResponseEntity<List<ExamDto>> getByExamType(@PathVariable String examType) {
        List<ExamDto> list = examService.getDtosByExamType(examType);
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Get Exams by Date",
            description = "Retrieve all exams scheduled on a specific date (format: yyyy-MM-dd).",
            tags = {"Exam Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of exams scheduled on the date returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid date format")
    })
    @GetMapping("/date/{date}")
    public ResponseEntity<List<ExamDto>> getByDate(@PathVariable String date) {
        List<ExamDto> list = examService.getDtosByDate(date);
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Get Exams by Student ID, Exam Type and Result",
            description = "Retrieve exams for a student filtered by exam type and result status.",
            tags = {"Exam Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filtered list of exams returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid exam type or result value")
    })
    @GetMapping("/student-id/{studentId}/exam-type/{examType}/result/{result}")
    public ResponseEntity<List<ExamDto>> getByStudentIdAndExamTypeAndResult(
            @PathVariable Long studentId,
            @PathVariable String examType,
            @PathVariable String result) {
        List<ExamDto> list = examService.getDtosByStudentIdAndExamTypeAndResult(studentId, examType, result);
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Get Exams by Student ID and Result",
            description = "Retrieve exams for a student filtered by result status.",
            tags = {"Exam Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filtered list of exams returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid result value")
    })
    @GetMapping("/student-id/{studentId}/result/{result}")
    public ResponseEntity<List<ExamDto>> getByStudentIdAndResult(
            @PathVariable Long studentId,
            @PathVariable String result) {
        List<ExamDto> list = examService.getDtosByStudentIdAndResult(studentId, result);
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Get Exams by Instructor ID and Result",
            description = "Retrieve exams conducted by an instructor filtered by result status.",
            tags = {"Exam Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filtered list of exams returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid result value")
    })
    @GetMapping("/instructor-id/{instructorId}/result/{result}")
    public ResponseEntity<List<ExamDto>> getByInstructorIdAndResult(
            @PathVariable Long instructorId,
            @PathVariable String result) {
        List<ExamDto> list = examService.getDtosByInstructorIdAndResult(instructorId, result);
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Get Exams by Instructor ID and Date",
            description = "Retrieve exams conducted by an instructor on a specific date (yyyy-MM-dd).",
            tags = {"Exam Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of exams returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid date format")
    })
    @GetMapping("/instructor-id/{instructorId}/date/{date}")
    public ResponseEntity<List<ExamDto>> getByInstructorIdAndDate(
            @PathVariable Long instructorId,
            @PathVariable String date) {
        List<ExamDto> list = examService.getDtosByInstructorIdAndDate(instructorId, date);
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Get Exams by Category and Date",
            description = "Retrieve exams filtered by license category and scheduled date.",
            tags = {"Exam Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of exams returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid category or date format")
    })
    @GetMapping("/category/{category}/date/{date}")
    public ResponseEntity<List<ExamDto>> getByCategoryAndDate(
            @PathVariable String category,
            @PathVariable String date) {
        List<ExamDto> list = examService.getDtosByCategoryAndDate(category, date);
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Get Exams by Exam Type and Date",
            description = "Retrieve exams filtered by exam type and scheduled date.",
            tags = {"Exam Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of exams returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid exam type or date format")
    })
    @GetMapping("/exam-type/{examType}/date/{date}")
    public ResponseEntity<List<ExamDto>> getByExamTypeAndDate(
            @PathVariable String examType,
            @PathVariable String date) {
        List<ExamDto> list = examService.getDtosByExamTypeAndDate(examType, date);
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Create a new Exam",
            description = "Save a new exam with the provided details.",
            tags = {"Exam Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Exam created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid exam data")
    })
    @PostMapping("/create")
    public ResponseEntity<ExamDto> save(@RequestBody ExamDtoRequest request) {
        ExamDto saved = examService.save(request);
        return ResponseEntity.status(201).body(saved);
    }

    @Operation(
            summary = "Update an existing Exam",
            description = "Update an existing exam with the provided details.",
            tags = {"Exam Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exam updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid exam data"),
            @ApiResponse(responseCode = "404", description = "Exam not found")
    })
    @PutMapping("/update")
    public ResponseEntity<ExamDto> update(@RequestBody ExamDtoRequest request) {
        ExamDto updated = examService.update(request);
        return ResponseEntity.ok(updated);
    }

    @Operation(
            summary = "Give Grade to Exam",
            description = "Assign a grade/result and optional remarks to an exam by its ID.",
            tags = {"Exam Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grade assigned successfully"),
            @ApiResponse(responseCode = "404", description = "Exam not found"),
            @ApiResponse(responseCode = "400", description = "Invalid grade or remarks")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    @PatchMapping("/grade/{examId}")
    public ResponseEntity<ExamDto> giveGrade(
            @PathVariable Long examId,
            @RequestParam String result,
            @RequestParam(required = false) String remarks) {
        ExamDto graded = examService.giveGrade(examId, result, remarks);
        return ResponseEntity.ok(graded);
    }
}

