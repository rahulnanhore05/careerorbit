package io.rahulnanhore.mapper;

import io.rahulnanhore.dto.response.JobSkillResponse;
import io.rahulnanhore.model.JobSkill;

public class JobSkillMapper {

    public static JobSkillResponse toJobSkillResponse(JobSkill skill){
        return JobSkillResponse.builder()
                .id(skill.getId())
                .name(skill.getName())
                .slug(skill.getSlug())
                .category(skill.getCategory())
                .active(skill.isActive())
                .createdAt(skill.getCreatedAt())
                .updatedAt(skill.getUpdatedAt())
                .build();
    }
}
