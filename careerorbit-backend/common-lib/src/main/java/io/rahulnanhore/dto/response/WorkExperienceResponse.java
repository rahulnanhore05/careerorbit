package io.rahulnanhore.dto.response;

import io.rahulnanhore.domain.JobType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkExperienceResponse {
    private Long id;
    private Long resumeId;
    private String companyName;
    private String companyLogoUrl;
    private String jobTitle;
    private JobType employmentType;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isCurrentJob;
    private String description;
    private List<String> technologies = new ArrayList<>();
    private Integer displayOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
