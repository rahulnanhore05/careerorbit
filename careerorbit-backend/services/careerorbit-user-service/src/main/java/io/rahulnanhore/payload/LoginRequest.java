package io.rahulnanhore.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @Email(message = "Invalid email")
    @NotBlank(message = "Full name is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

}
