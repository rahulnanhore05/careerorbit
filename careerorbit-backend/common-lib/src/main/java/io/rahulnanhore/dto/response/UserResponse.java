package io.rahulnanhore.dto.response;

import io.rahulnanhore.domain.UserRole;
import io.rahulnanhore.domain.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private long id;
    private String fullName;
    private String email;
    private String phone;
    private String profileImage;
    private UserRole role = UserRole.ROLE_JOB_SEEKER;
    private UserStatus status = UserStatus.ACTIVE;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
}
