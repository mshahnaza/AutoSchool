package org.example.autoschool.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.autoschool.config.JWTService;
import org.example.autoschool.dto.request.InstructorDtoRequest;
import org.example.autoschool.dto.request.LoginRequest;
import org.example.autoschool.dto.request.StudentDtoRequest;
import org.example.autoschool.dto.response.AuthResponse;
import org.example.autoschool.dto.response.InstructorDto;
import org.example.autoschool.dto.response.StudentDto;
import org.example.autoschool.entity.Student;
import org.example.autoschool.entity.User;
import org.example.autoschool.enums.Role;
import org.example.autoschool.enums.TokenType;
import org.example.autoschool.service.TokenService;
import org.example.autoschool.service.UserService;
import org.example.autoschool.utils.exception.NoTokenProvided;
import org.example.autoschool.utils.exception.VerificationException;
import org.example.autoschool.utils.mapper.StudentMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final StudentService studentService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public Map<String, String> registerStudent(StudentDtoRequest request) {
        var user = User.builder()
                .email(request.getUser().getEmail())
                .name(request.getUser().getName())
                .phone(request.getUser().getPhone() == null ? "" : request.getUser().getPhone())
                .password(passwordEncoder.encode(request.getUser().getPassword()))
                .role(Role.STUDENT)
                .dateOfBirth(request.getUser().getDateOfBirth())
                .build();

        var student = Student.builder()
                .studentUser(user)
                .passportId(request.getPassportId())
                .build();
        studentService.save(student);
        return sendTokenResponse(user);
    }

    public Map<String, String> registerInstructor(InstructorDtoRequest request) {
        var user = User.builder()
                .email(request.getUser().getEmail())
                .name(request.getUser().getName())
                .phone(request.getUser().getPhone() == null ? "" : request.getUser().getPhone())
                .password(passwordEncoder.encode(request.getUser().getPassword()))
                .role(Role.INSTRUCTOR)
                .dateOfBirth(request.getUser().getDateOfBirth())
                .build();

        var instructor = Student.builder()
                .studentUser(user)
                .passportId(request.getLicenseNumber())
                .build();
        studentService.save(instructor);
        return sendTokenResponse(user);
    }

    public Map<String, String> login(LoginRequest request) throws IOException {
        var user = userService.getEntityByEmail(request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        return sendTokenResponse(user);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new NoTokenProvided();
        }

        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);

        if (username != null) {
            var userDetails = this.userService.getEntityByEmail(username);

            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                tokenService.revokeAllUserTokens(userDetails);
                var accessToken = jwtService.generateToken(userDetails);
                tokenService.saveUserToken(userDetails, accessToken, TokenType.BEARER);
                var authResponse = AuthResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();

                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public Map<String, String> sendTokenResponse(User user) {
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        tokenService.saveUserToken(user, accessToken, TokenType.BEARER);
        tokenService.saveUserToken(user, refreshToken, TokenType.REFRESH);

        Map<String, String> tokens = Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        );

        return tokens;
    }
}
