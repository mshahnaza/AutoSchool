package org.example.autoschool.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.AvailableSlotDtoRequest;
import org.example.autoschool.dto.response.AvailableSlotDto;
import org.example.autoschool.service.AvailableSlotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/slot")
@RequiredArgsConstructor
@Tag(name = "Available Slot Management", description = "Operations related to managing available slot entities in the autoSchool system")
public class AvailableSlotController {

    private final AvailableSlotService slotService;

    @Operation(
            summary = "Get AvailableSlot by ID",
            description = "Retrieve an available slot by its unique ID.",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slot successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Slot not found")
    })
    @GetMapping("/by-id/{id}")
    public ResponseEntity<AvailableSlotDto> getSlotById(@PathVariable Long id) {
        AvailableSlotDto dto = slotService.getDtoById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Get all AvailableSlots",
            description = "Retrieve a list of all available slots.",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponse(responseCode = "200", description = "List of available slots retrieved")
    @GetMapping("/get-all")
    public ResponseEntity<List<AvailableSlotDto>> getAllSlots() {
        List<AvailableSlotDto> slots = slotService.getAllDtos();
        return ResponseEntity.ok(slots);
    }

    @Operation(
            summary = "Get AvailableSlots by Exam Day ID",
            description = "Retrieve a list of available slots filtered by the given exam day ID.",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slots successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "No slots found for the given exam day ID")
    })
    @GetMapping("/exam-day-id/{examDayId}")
    public ResponseEntity<List<AvailableSlotDto>> getSlotsByExamDayId(@PathVariable Long examDayId) {
        List<AvailableSlotDto> slots = slotService.getDtoByExamDayId(examDayId);
        return ResponseEntity.ok(slots);
    }

    @Operation(
            summary = "Get AvailableSlots by Instructor ID",
            description = "Retrieve a list of available slots filtered by the given instructor ID.",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slots successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "No slots found for the given instructor ID")
    })
    @GetMapping("/instructor-id/{instructorId}")
    public ResponseEntity<List<AvailableSlotDto>> getSlotsByInstructorId(@PathVariable Long instructorId) {
        List<AvailableSlotDto> slots = slotService.getDtoByInstructorId(instructorId);
        return ResponseEntity.ok(slots);
    }

    @Operation(
            summary = "Get AvailableSlots by Booking Status",
            description = "Retrieve a list of available slots filtered by booking status (true or false).",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slots successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "No slots found for the given booking status")
    })
    @GetMapping("/booked")
    public ResponseEntity<List<AvailableSlotDto>> getSlotsByIsBooked(@RequestParam Boolean isBooked) {
        List<AvailableSlotDto> slots = slotService.getDtoByIsBooked(isBooked);
        return ResponseEntity.ok(slots);
    }

    @Operation(
            summary = "Get AvailableSlots by Time",
            description = "Retrieve a list of available slots filtered by the given time (HH:mm:ss format).",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slots successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid time format")
    })
    @GetMapping("/time")
    public ResponseEntity<List<AvailableSlotDto>> getSlotsByTime(@RequestParam String time) {
        List<AvailableSlotDto> slots = slotService.getDtoByTime(time);
        return ResponseEntity.ok(slots);
    }

    @Operation(
            summary = "Get AvailableSlots by ExamDay ID, Instructor ID, Booking Status, and Time",
            description = "Retrieve available slots filtered by exam day ID, instructor ID, booking status, and time (HH:mm:ss).",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slots successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters")
    })
    @GetMapping("/filter/examDay/{examDayId}/instructor/{instructorId}/booked/{isBooked}/time")
    public ResponseEntity<List<AvailableSlotDto>> getSlotsByExamDayInstructorBookedTime(
            @PathVariable Long examDayId,
            @PathVariable Long instructorId,
            @PathVariable Boolean isBooked,
            @RequestParam String time) {
        List<AvailableSlotDto> slots = slotService.getDtoByExamDayIdAndInstructorIdAndIsBookedAndTime(
                examDayId, instructorId, isBooked, time);
        return ResponseEntity.ok(slots);
    }

    @Operation(
            summary = "Get AvailableSlots by ExamDay ID, Instructor ID, and Booking Status",
            description = "Retrieve available slots filtered by exam day ID, instructor ID, and booking status.",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slots successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters")
    })
    @GetMapping("/filter/examDay/{examDayId}/instructor/{instructorId}/booked/{isBooked}")
    public ResponseEntity<List<AvailableSlotDto>> getSlotsByExamDayInstructorBooked(
            @PathVariable Long examDayId,
            @PathVariable Long instructorId,
            @PathVariable Boolean isBooked) {
        List<AvailableSlotDto> slots = slotService.getDtoByExamDayIdAndInstructorIdAndIsBooked(
                examDayId, instructorId, isBooked);
        return ResponseEntity.ok(slots);
    }

    @Operation(
            summary = "Get AvailableSlots by ExamDay ID, Instructor ID, and Time",
            description = "Retrieve available slots filtered by exam day ID, instructor ID, and time (HH:mm:ss).",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slots successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters")
    })
    @GetMapping("/filter/examDay/{examDayId}/instructor/{instructorId}/time")
    public ResponseEntity<List<AvailableSlotDto>> getSlotsByExamDayInstructorTime(
            @PathVariable Long examDayId,
            @PathVariable Long instructorId,
            @RequestParam String time) {
        List<AvailableSlotDto> slots = slotService.getDtoByExamDayIdAndInstructorIdAndTime(
                examDayId, instructorId, time);
        return ResponseEntity.ok(slots);
    }

    @Operation(
            summary = "Get AvailableSlots by ExamDay ID, Booking Status, and Time",
            description = "Retrieve available slots filtered by exam day ID, booking status, and time (HH:mm:ss).",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slots successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters")
    })
    @GetMapping("/filter/examDay/{examDayId}/booked/{isBooked}/time")
    public ResponseEntity<List<AvailableSlotDto>> getSlotsByExamDayBookedTime(
            @PathVariable Long examDayId,
            @PathVariable Boolean isBooked,
            @RequestParam String time) {
        List<AvailableSlotDto> slots = slotService.getDtoByExamDayIdAndIsBookedAndTime(
                examDayId, isBooked, time);
        return ResponseEntity.ok(slots);
    }

    @Operation(
            summary = "Get AvailableSlots by Instructor ID, Booking Status, and Time",
            description = "Retrieve available slots filtered by instructor ID, booking status, and time (HH:mm:ss).",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slots successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters")
    })
    @GetMapping("/filter/instructor/{instructorId}/booked/{isBooked}/time")
    public ResponseEntity<List<AvailableSlotDto>> getSlotsByInstructorBookedTime(
            @PathVariable Long instructorId,
            @PathVariable Boolean isBooked,
            @RequestParam String time) {
        List<AvailableSlotDto> slots = slotService.getDtoByInstructorIdAndisBookedAndTime(instructorId, isBooked, time);
        return ResponseEntity.ok(slots);
    }

    @Operation(
            summary = "Get AvailableSlots by ExamDay ID and Instructor ID",
            description = "Retrieve available slots filtered by exam day ID and instructor ID.",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slots successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters")
    })
    @GetMapping("/filter/examDay/{examDayId}/instructor/{instructorId}")
    public ResponseEntity<List<AvailableSlotDto>> getSlotsByExamDayAndInstructor(
            @PathVariable Long examDayId,
            @PathVariable Long instructorId) {
        List<AvailableSlotDto> slots = slotService.getDtoByExamDayIdAndInstructorId(examDayId, instructorId);
        return ResponseEntity.ok(slots);
    }

    @Operation(
            summary = "Get AvailableSlots by ExamDay ID and Booking Status",
            description = "Retrieve available slots filtered by exam day ID and booking status.",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slots successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters")
    })
    @GetMapping("/filter/examDay/{examDayId}/booked/{isBooked}")
    public ResponseEntity<List<AvailableSlotDto>> getSlotsByExamDayAndBooked(
            @PathVariable Long examDayId,
            @PathVariable Boolean isBooked) {
        List<AvailableSlotDto> slots = slotService.getDtoByExamDayIdAndIsBooked(examDayId, isBooked);
        return ResponseEntity.ok(slots);
    }

    @Operation(
            summary = "Get AvailableSlots by ExamDay ID and Time",
            description = "Retrieve available slots filtered by exam day ID and time (HH:mm:ss).",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slots successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters")
    })
    @GetMapping("/filter/examDay/{examDayId}/time")
    public ResponseEntity<List<AvailableSlotDto>> getSlotsByExamDayAndTime(
            @PathVariable Long examDayId,
            @RequestParam String time) {
        List<AvailableSlotDto> slots = slotService.getDtoByExamDayIdAndTime(examDayId, time);
        return ResponseEntity.ok(slots);
    }

    @Operation(
            summary = "Get Available Slots by Instructor ID and Booking Status",
            description = "Retrieve available slots filtered by instructor ID and whether the slot is booked.",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slots successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied")
    })
    @GetMapping("/filter/instructor/{instructorId}/booked/{isBooked}")
    public ResponseEntity<List<AvailableSlotDto>> getSlotsByInstructorAndBooked(
            @PathVariable Long instructorId,
            @PathVariable Boolean isBooked) {
        List<AvailableSlotDto> slots = slotService.getDtoByInstructorIdAndIsBooked(instructorId, isBooked);
        return ResponseEntity.ok(slots);
    }

    @Operation(
            summary = "Get Available Slots by Instructor ID and Time",
            description = "Retrieve available slots filtered by instructor ID and time (HH:mm:ss).",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slots successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid time format")
    })
    @GetMapping("/filter/instructor/{instructorId}/time")
    public ResponseEntity<List<AvailableSlotDto>> getSlotsByInstructorAndTime(
            @PathVariable Long instructorId,
            @RequestParam String time) {
        List<AvailableSlotDto> slots = slotService.getDtoByInstructorIdAndTime(instructorId, time);
        return ResponseEntity.ok(slots);
    }

    @Operation(
            summary = "Get Available Slots by Booking Status and Time",
            description = "Retrieve available slots filtered by booking status and time (HH:mm:ss).",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slots successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid time format")
    })
    @GetMapping("/filter/booked/{isBooked}/time")
    public ResponseEntity<List<AvailableSlotDto>> getSlotsByBookedAndTime(
            @PathVariable Boolean isBooked,
            @RequestParam String time) {
        List<AvailableSlotDto> slots = slotService.getDtoByIsBookedAndTime(isBooked, time);
        return ResponseEntity.ok(slots);
    }

    @Operation(
            summary = "Create a new AvailableSlot",
            description = "Create a new available slot with given details.",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Slot successfully created"),
            @ApiResponse(responseCode = "409", description = "Slot already exists with given exam day, instructor, and time")
    })
    @PostMapping("/create")
    public ResponseEntity<AvailableSlotDto> createSlot(@RequestBody AvailableSlotDtoRequest request) {
        AvailableSlotDto savedSlot = slotService.save(request);
        return new ResponseEntity<>(savedSlot, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update an existing AvailableSlot",
            description = "Update details of an existing available slot identified by ID.",
            tags = {"AvailableSlot Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slot successfully updated"),
            @ApiResponse(responseCode = "404", description = "Slot not found"),
            @ApiResponse(responseCode = "409", description = "Slot overload or max students reached")
    })
    @PutMapping("/update")
    public ResponseEntity<AvailableSlotDto> updateSlot(@RequestBody AvailableSlotDtoRequest request) {
        AvailableSlotDto updatedSlot = slotService.update(request);
        return ResponseEntity.ok(updatedSlot);
    }
}

