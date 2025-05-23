package org.example.autoschool.service;

import jakarta.servlet.http.HttpServletResponse;
import org.example.autoschool.config.JWTService;
import org.example.autoschool.entity.Token;
import org.example.autoschool.entity.User;
import org.example.autoschool.enums.TokenType;

public interface TokenService {
    Token getByToken(String token);

    void deleteByToken(String token);

    void saveUserToken(User user, String jwtToken, TokenType tokenType);

    void setTokenCookies(HttpServletResponse response, JWTService jwtService, String accessToken, String refreshToken);

    void revokeAllUserTokens(User user);
}
