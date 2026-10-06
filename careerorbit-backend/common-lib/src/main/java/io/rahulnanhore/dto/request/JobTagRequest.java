package io.rahulnanhore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobTagRequest {

    @NotBlank(message = "Name cannot be null")
    private String name;
}
