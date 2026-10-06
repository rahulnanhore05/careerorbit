package io.rahulnanhore.controller;

import io.rahulnanhore.payload.AuthResponse;
import io.rahulnanhore.payload.LoginRequest;
import io.rahulnanhore.payload.SignupRequest;
import io.rahulnanhore.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(
            @RequestBody @Valid SignupRequest request) throws Exception {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid LoginRequest request) throws Exception {
        return ResponseEntity.ok(authService.login(request));
    }
}
