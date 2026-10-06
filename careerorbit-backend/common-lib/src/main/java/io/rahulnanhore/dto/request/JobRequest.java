package io.rahulnanhore.dto.request;

import io.rahulnanhore.domain.ExperienceLevel;
import io.rahulnanhore.domain.JobStatus;
import io.rahulnanhore.domain.JobType;
import io.rahulnanhore.domain.WorkMode;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class JobRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    private String responsibilities;
    private String requirements;
    private String benefits;

    @NotNull(message = "Category is required")
    private Long categoryId;
    private Set<Long> skillIds;
    private Set<Long> tagIds;

    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    @DecimalMin(value = "0.0", message = "Salary must be greater than or equal to 0")
    private BigDecimal minSalary;

    @DecimalMin(value = "0.0", message = "Salary must be greater than or equal to 0")
    private BigDecimal maxSalary;

    @NotNull(message = "Job type is required")
    private JobType jobType;

    @NotNull(message = "Work mode is required")
    private WorkMode workMode;

    @NotNull(message = "Experience level is required")
    private ExperienceLevel experienceLevel;

    private JobStatus status;

    @Min(value = 1, message = "Opening must be greater than or equal to 1")
    private Integer openings = 1;
    private LocalDateTime applicationDeadline;
    private LocalDateTime expiredAt;
}
