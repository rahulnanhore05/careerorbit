package io.rahulnanhore.mapper;

import io.rahulnanhore.dto.response.UserResponse;
import io.rahulnanhore.model.User;

import java.util.List;

public class UserMapper {
    public static UserResponse toDTO(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFullName(user.getFullName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setProfileImage(user.getProfileImage());
        userResponse.setRole(user.getRole());
        userResponse.setStatus(user.getStatus());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setLastLogin(user.getLastLogin());
        return userResponse;
    }

    public static List<UserResponse> toDTOList(List<User> users){
        return users.stream().map(UserMapper::toDTO).toList();
    }
}
