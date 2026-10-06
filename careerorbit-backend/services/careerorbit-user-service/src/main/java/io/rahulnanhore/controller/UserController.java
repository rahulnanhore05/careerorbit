package io.rahulnanhore.controller;

import io.rahulnanhore.dto.response.UserResponse;
import io.rahulnanhore.mapper.UserMapper;
import io.rahulnanhore.model.User;
import io.rahulnanhore.payload.UserUpdateRequest;
import io.rahulnanhore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getProfile(@RequestHeader("X-User-Email") String email) throws Exception {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @PutMapping("/profile")
    public ResponseEntity<UserResponse> updateProfile(@RequestHeader("X-User-Email") String email, @RequestBody UserUpdateRequest request) throws Exception {
        return ResponseEntity.ok(userService.updateUser(email, request));
    }

    @GetMapping("/{user}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("user") long userId) throws Exception {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(UserMapper.toDTOList(users));
    }

    @PatchMapping("/{user}/suspend")
    public ResponseEntity<UserResponse> suspendUser(@PathVariable("user") long userId) throws Exception {
        UserResponse user = userService.suspendUser(userId);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{user}/activate")
    public ResponseEntity<UserResponse> activateUser(@PathVariable("user") long userId) throws Exception {
        UserResponse user = userService.activateUser(userId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{user}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable("user") long userId) throws Exception {
        UserResponse user = userService.deleteUser(userId);
        return ResponseEntity.ok(user);
    }
}
