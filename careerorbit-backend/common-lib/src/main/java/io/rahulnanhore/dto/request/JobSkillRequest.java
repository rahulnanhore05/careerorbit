package io.rahulnanhore.dto.request;

import io.rahulnanhore.domain.SkillCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobSkillRequest {

    @NotBlank(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Category cannot be null")
    private SkillCategory category;
}
