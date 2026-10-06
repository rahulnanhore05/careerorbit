package io.rahulnanhore.mapper;

import io.rahulnanhore.dto.response.EducationResponse;
import io.rahulnanhore.model.Education;

public class EducationMapper {
    public static EducationResponse toEducationResponse(Education education){
        if (education == null) return new EducationResponse();

        return EducationResponse.builder()
                .id(education.getId())
                .institutionName(education.getInstitutionName())
                .degree(education.getDegree())
                .fieldOfStudy(education.getFieldOfStudy())
                .grade(education.getGrade())
                .startDate(education.getStartDate())
                .endDate(education.getEndDate())
                .isCurrentlyStudying(education.getIsCurrentlyStudying())
                .description(education.getDescription())
                .displayOrder(education.getDisplayOrder())
                .createdAt(education.getCreatedAt())
                .updatedAt(education.getUpdatedAt())
                .build();
    }
}
