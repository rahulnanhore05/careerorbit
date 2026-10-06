package io.rahulnanhore.dto.response;

import io.rahulnanhore.domain.ExperienceLevel;
import io.rahulnanhore.domain.JobStatus;
import io.rahulnanhore.domain.JobType;
import io.rahulnanhore.domain.WorkMode;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JobResponse {

    private Long id;
    private String title;
    private String description;
    private String responsibilities;
    private String requirements;
    private String benefits;


    private CompanyResponse company;
    private Long employerId;

    private JobCategoryResponse category;
    private Set<JobSkillResponse> skills;
    private Set<JobTagResponse> tags;

    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    private BigDecimal minSalary;
    private BigDecimal maxSalary;

    private JobType jobType;
    private WorkMode workMode;
    private ExperienceLevel experienceLevel;
    private JobStatus status;

    private Integer openings;
    private LocalDateTime applicationDeadline;
    private LocalDateTime expiredAt;
    private Boolean active;

    private LocalDateTime publishedAt;
    private LocalDateTime closedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
