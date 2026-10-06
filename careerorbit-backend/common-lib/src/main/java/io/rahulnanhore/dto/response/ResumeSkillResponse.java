package io.rahulnanhore.dto.response;

import io.rahulnanhore.domain.ProficiencyLevel;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeSkillResponse {

    private Long id;

    private String skillName;

    private ProficiencyLevel proficiencyLevel;

    private Integer yearsOfExperience;

    private Integer displayOrder;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
