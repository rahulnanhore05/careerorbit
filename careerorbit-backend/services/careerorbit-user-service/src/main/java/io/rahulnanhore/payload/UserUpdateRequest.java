package io.rahulnanhore.payload;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String fullName;
    private String phone;
    private String profileImage;
}
