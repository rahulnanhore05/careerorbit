package io.rahulnanhore.event;

import io.rahulnanhore.domain.ApplicationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationStatusChangedEvent {
    private Long applicationId;
    private Long candidateId;
    private String candidateEmail;
    private String candidateName;
    private ApplicationStatus newStatus;
    private String note;
    private String jobTitle;
    private String companyName;
    private LocalDateTime changedAt;
}
