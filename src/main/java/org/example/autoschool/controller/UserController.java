package org.example.autoschool.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.UserDtoRequest;
import org.example.autoschool.dto.response.UserDto;
import org.example.autoschool.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "Operations related to managing user entities in the autoSchool system")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Retrieve user by ID",
            description = "This operation allows clients to retrieve a user using their unique ID.",
            tags = {"User Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/by-id/{id}")
    public ResponseEntity<UserDto> getUserById(
            @Parameter(description = "ID of the user to retrieve") @PathVariable Long id) {
        return ResponseEntity.ok(userService.getDtoById(id));
    }

    @Operation(
            summary = "Retrieve user by email",
            description = "This operation allows clients to retrieve a user using their email address.",
            tags = {"User Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/by-email")
    public ResponseEntity<UserDto> getUserByEmail(
            @Parameter(description = "Email address of the user to retrieve") @RequestParam String email) {
        return ResponseEntity.ok(userService.getDtoByEmail(email));
    }

    @Operation(
            summary = "Retrieve all users",
            description = "This operation allows admins to retrieve a list of all users registered in the system.",
            tags = {"Admin Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all users"),
            @ApiResponse(responseCode = "404", description = "Users not found")
    })
    @GetMapping("/get-all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllDtos());
    }

    @Operation(
            summary = "Create a new user",
            description = "This operation allows clients to create a new user in the system.",
            tags = {"User Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid user input")
    })
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(
            @Parameter(description = "User data to be created") @RequestBody UserDtoRequest request) {
        return ResponseEntity.ok(userService.save(request));
    }

    @Operation(
            summary = "Update an existing user",
            description = "This operation allows clients to update an existing user's information.",
            tags = {"User Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully updated"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid update data")
    })
    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(
            @Parameter(description = "Updated user data") @RequestBody UserDtoRequest request) {
        return ResponseEntity.ok(userService.update(request));
    }
}
