package io.rahulnanhore.payload;

import io.rahulnanhore.domain.AiShortListStatus;
import io.rahulnanhore.domain.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyApplicationFilter {
    private Long jobId;
    private ApplicationStatus status;
    private Boolean isStarred;
    private AiShortListStatus aiShortListStatus;
    private Integer minAiScore;
    private String sortBy;
}
