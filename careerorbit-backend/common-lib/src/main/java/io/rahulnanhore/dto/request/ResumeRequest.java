package io.rahulnanhore.dto.request;

import io.rahulnanhore.domain.ResumeTemplate;
import io.rahulnanhore.domain.ResumeVisibility;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private ResumeTemplate template;

    private ResumeVisibility visibility;

    private Boolean isDefault;
}
