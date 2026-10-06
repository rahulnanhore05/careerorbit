package io.rahulnanhore.dto.response;

import io.rahulnanhore.domain.SkillCategory;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSkillResponse {
    private Long id;
    private String name;
    private String slug;
    private SkillCategory category;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
