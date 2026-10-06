package io.rahulnanhore.service.impl;

import io.rahulnanhore.domain.UserRole;
import io.rahulnanhore.domain.UserStatus;
import io.rahulnanhore.mapper.UserMapper;
import io.rahulnanhore.model.User;
import io.rahulnanhore.payload.AuthResponse;
import io.rahulnanhore.payload.LoginRequest;
import io.rahulnanhore.payload.SignupRequest;
import io.rahulnanhore.repository.UserRepository;
import io.rahulnanhore.security.JwtProvider;
import io.rahulnanhore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signup(SignupRequest request) throws Exception {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new Exception("Email already registered");
        }

        if (request.getRole() == UserRole.ROLE_ADMIN) {
            throw new Exception("Admin cannot be registered");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(request.getRole())
                .status(UserStatus.ACTIVE)
                .lastLogin(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);
        String token = jwtProvider.generateToken(savedUser);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setTitle("Welcome " + savedUser.getFullName());
        authResponse.setMessage("Registered successfully");
        authResponse.setJwt(token);
        authResponse.setUserResponse(UserMapper.toDTO(savedUser));


        return authResponse;
    }

    @Override
    public AuthResponse login(LoginRequest request) throws Exception {
        Authentication authentication = this.authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new Exception("User not found"));
        String token = jwtProvider.generateToken(user);

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setTitle("Welcome back " + user.getFullName());
        authResponse.setMessage("Login successfully");
        authResponse.setJwt(token);
        authResponse.setUserResponse(UserMapper.toDTO(user));

        return authResponse;
    }

    private Authentication authenticate(LoginRequest request) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new Exception("Invalid credentials");
        }
    }
}
