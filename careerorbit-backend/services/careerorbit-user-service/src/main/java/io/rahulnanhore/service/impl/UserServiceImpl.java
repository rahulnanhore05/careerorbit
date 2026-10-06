package io.rahulnanhore.service.impl;

import io.rahulnanhore.domain.UserStatus;
import io.rahulnanhore.dto.response.UserResponse;
import io.rahulnanhore.mapper.UserMapper;
import io.rahulnanhore.model.User;
import io.rahulnanhore.payload.UserUpdateRequest;
import io.rahulnanhore.repository.UserRepository;
import io.rahulnanhore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) throws Exception{
        return userRepository.findByEmail(email).orElseThrow(() -> new Exception("User not found"));
    }

    @Override
    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse updateUser(String email, UserUpdateRequest request) throws Exception {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new Exception("User not found"));

        if(request.getFullName() != null && !request.getFullName().isBlank()) user.setFullName(request.getFullName());
        if(request.getPhone() != null && !request.getPhone().isBlank()) user.setPhone(request.getPhone());
        if(request.getProfileImage() != null && !request.getProfileImage().isBlank()) user.setProfileImage(request.getProfileImage());

        User updatedUser = userRepository.save(user);
        return UserMapper.toDTO(updatedUser);
    }

    @Override
    public UserResponse suspendUser(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        user.setStatus(UserStatus.SUSPENDED);
        user.setSuspendedAt(LocalDateTime.now());

        return UserMapper.toDTO(user);
    }

    @Override
    public UserResponse activateUser(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        user.setStatus(UserStatus.ACTIVE);

        return UserMapper.toDTO(user);
    }

    @Override
    public UserResponse deleteUser(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        user.setStatus(UserStatus.DELETED);
        user.setDeleteAt(LocalDateTime.now());

        return UserMapper.toDTO(user);
    }
}
