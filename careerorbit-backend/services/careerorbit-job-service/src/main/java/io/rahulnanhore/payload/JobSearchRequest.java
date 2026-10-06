package io.rahulnanhore.payload;

import io.rahulnanhore.domain.ExperienceLevel;
import io.rahulnanhore.domain.JobStatus;
import io.rahulnanhore.domain.JobType;
import io.rahulnanhore.domain.WorkMode;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JobSearchRequest {

    private String keyword;

    private Long categoryId;

    private List<Long> skillIds;

    private List<Long> tagIds;

    private Long companyId;

    private String location;

    private BigDecimal minSalary;

    private BigDecimal maxSalary;

    private JobType jobType;

    private WorkMode workMode;

    private ExperienceLevel experienceLevel;

    private JobStatus status;

    private Integer minOpenings;

    private Integer maxOpenings;
}
