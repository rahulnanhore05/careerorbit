package io.rahulnanhore.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationRequest {
    @NotNull(message = "Job id is required")
    private Long jobId;

    @NotNull(message = "Resume id is required")
    private Long resumeId;

    private String coverLetter;

    @Min(value = 0, message = "Expected salary must be non-negative")
    private BigDecimal expectedSalary;
    private LocalDate availableFrom;
}
