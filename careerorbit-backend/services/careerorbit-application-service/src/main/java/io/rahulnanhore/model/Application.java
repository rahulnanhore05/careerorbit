package io.rahulnanhore.model;

import io.rahulnanhore.domain.AiShortListStatus;
import io.rahulnanhore.domain.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long candidateId;

    @Column(nullable = false)
    private Long jobId;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private Long employerId;

    @Column(nullable = false)
    private Long resumeId;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.PENDING;

    private String coverLetter;

    private BigDecimal expectedSalary;

    private LocalDate availableFrom;

    @Builder.Default
    private Boolean isStarred = false;

    private Integer aiScore;

    private AiShortListStatus aiShortListStatus;

    private LocalDate withdrawnAt;

    private String withdrawalReason;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
