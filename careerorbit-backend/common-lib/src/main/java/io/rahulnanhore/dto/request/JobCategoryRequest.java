package io.rahulnanhore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobCategoryRequest {

    @NotBlank(message = "Name is required")
    private String name;
    private String description;
    private String iconUrl;
    private Long parentId;
}
