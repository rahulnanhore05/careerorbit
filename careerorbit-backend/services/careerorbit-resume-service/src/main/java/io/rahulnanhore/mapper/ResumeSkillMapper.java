package io.rahulnanhore.mapper;

import io.rahulnanhore.dto.response.ResumeSkillResponse;
import io.rahulnanhore.model.ResumeSkill;

public class ResumeSkillMapper {

    public static ResumeSkillResponse toResumeSkillResponse(ResumeSkill skill){
        if (skill == null) return new ResumeSkillResponse();

        return ResumeSkillResponse.builder()
                .id(skill.getId())
                .skillName(skill.getSkillName())
                .proficiencyLevel(skill.getProficiencyLevel())
                .yearsOfExperience(skill.getYearsOfExperience())
                .displayOrder(skill.getDisplayOrder())
                .createdAt(skill.getCreatedAt())
                .updatedAt(skill.getUpdatedAt())
                .build();
    }

}
