package org.example.autoschool.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.autoschool.dto.request.InstructorDtoRequest;
import org.example.autoschool.dto.request.LoginRequest;
import org.example.autoschool.dto.request.StudentDtoRequest;
import org.example.autoschool.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints related to user authentication and verification")
public class AuthController {
    private final AuthService authService;

    @Operation(
            summary = "Register a new student",
            description = "Registers a student",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Student registered successfully"),
                    @ApiResponse(responseCode = "400", description = "Registration failed")
            }
    )
    @PostMapping("/register-student")
    public ResponseEntity<?> register(@RequestBody StudentDtoRequest request) {
        try {
            Map<String, String> authResponse = authService.registerStudent(request);
            Map<String, Object> response;

            response = Map.of(
                    "message", "This is you tokens",
                    "tokens", authResponse
            );

            return ResponseEntity.ok(response);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + exception.getMessage());
        }
    }

    @Operation(
            summary = "Register a new instructor",
            description = "Registers an instructor",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Instructor registered successfully"),
                    @ApiResponse(responseCode = "400", description = "Registration failed")
            }
    )
    @PostMapping("/register-instructor")
    public ResponseEntity<?> register(@RequestBody InstructorDtoRequest request) {
        try {
            Map<String, String> authResponse = authService.registerInstructor(request);
            Map<String, Object> response;

            response = Map.of(
                    "message", "This is you tokens",
                    "tokens", authResponse
            );

            return ResponseEntity.ok(response);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + exception.getMessage());
        }
    }

    @Operation(
            summary = "Login with credentials",
            description = "Authenticates a user and sends a 2FA email if successful",
            responses = {
                    @ApiResponse(responseCode = "200", description = "2FA email sent"),
                    @ApiResponse(responseCode = "500", description = "Login failed")
            }
    )
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Map<String, String> authResponse = authService.login(request);
            Map<String, Object> response;

            response = Map.of(
                    "message", "This is you tokens",
                    "tokens", authResponse
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Login failed: " + e.getMessage()));
        }
    }

    @Operation(
            summary = "Refresh access token",
            description = "Generates a new access token from a valid refresh token",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Access token refreshed"),
                    @ApiResponse(responseCode = "401", description = "Invalid or missing refresh token")
            }
    )
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authService.refreshToken(request, response);
    }
}
