package io.rahulnanhore.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkExperienceBulletsRequest {

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    private String company;

    @NotBlank(message = "Raw description is required")
    private String rawDescription;
    private String achievementsHint;
}
