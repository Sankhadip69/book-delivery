package com.springframework.bookdelivery.controller;

import com.springframework.bookdelivery.payload.request.auth.LoginRequest;
import com.springframework.bookdelivery.payload.request.auth.SignupRequest;
import com.springframework.bookdelivery.payload.request.auth.TokenRefreshRequest;
import com.springframework.bookdelivery.payload.response.CustomResponse;
import com.springframework.bookdelivery.payload.response.auth.JWTResponse;
import com.springframework.bookdelivery.payload.response.auth.TokenRefreshResponse;
import com.springframework.bookdelivery.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<String> register(@RequestBody SignupRequest request) {

        return CustomResponse.created(authService.register(request));
    }

    @PostMapping("/login")
    public CustomResponse<JWTResponse> login(@RequestBody LoginRequest request) {

        return CustomResponse.ok(authService.login(request));
    }

    @PostMapping("/refreshtoken")
    public CustomResponse<TokenRefreshResponse> refreshToken(@RequestBody TokenRefreshRequest request) {

        return CustomResponse.ok(authService.refreshToken(request));
    }

    @PostMapping("/logout")
    public CustomResponse<String> logout(@RequestHeader("Authorization") String token) {

        return CustomResponse.ok(authService.logout(token));
    }
}
