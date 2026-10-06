package io.rahulnanhore.service;

import io.rahulnanhore.dto.response.UserResponse;
import io.rahulnanhore.model.User;
import io.rahulnanhore.payload.UserUpdateRequest;

import java.util.List;

public interface UserService {

    User getUserByEmail(String email) throws Exception;

    User getUserById(Long id) throws Exception;

    List<User> getAllUsers();

    UserResponse updateUser(String email, UserUpdateRequest request) throws Exception;

    // Admin methods
    UserResponse suspendUser(Long id) throws Exception;

    UserResponse activateUser(Long id) throws Exception;

    UserResponse deleteUser(Long id) throws Exception;
}
