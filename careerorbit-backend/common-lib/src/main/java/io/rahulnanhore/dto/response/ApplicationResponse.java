package io.rahulnanhore.dto.response;

import io.rahulnanhore.domain.ApplicationStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationResponse {
    private Long id;

    private UserResponse candidate;
    private JobResponse job;
    private CompanyResponse company;

    private ApplicationStatus status;

    private Long employerId;
    private Long resumeId;
    private String coverLetter;

    private BigDecimal expectedSalary;
    private LocalDate availableFrom;

    private Boolean isStarred;

    private List<ApplicationNoteResponse> notes;

    private LocalDate withdrawnAt;
    private String withdrawalReason;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
