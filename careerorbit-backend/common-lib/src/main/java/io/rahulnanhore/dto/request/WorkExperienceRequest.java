package io.rahulnanhore.dto.request;

import io.rahulnanhore.domain.JobType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkExperienceRequest {

    @NotBlank(message = "Company name is required")
    private String companyName;

    private String companyLogoUrl;

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @NotNull(message = "Employment type is required")
    private JobType employmentType;

    private String location;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean isCurrentJob;

    private String description;

    private List<String> technologies;

    private Integer displayOrder;
}
