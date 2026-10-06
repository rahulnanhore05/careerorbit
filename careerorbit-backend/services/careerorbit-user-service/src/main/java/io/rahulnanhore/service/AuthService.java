package io.rahulnanhore.service;

import io.rahulnanhore.payload.AuthResponse;
import io.rahulnanhore.payload.LoginRequest;
import io.rahulnanhore.payload.SignupRequest;

public interface AuthService {

    AuthResponse signup(SignupRequest request) throws Exception;
    AuthResponse login(LoginRequest request) throws Exception;
}
