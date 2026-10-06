package io.rahulnanhore.mapper;

import io.rahulnanhore.dto.response.WorkExperienceResponse;
import io.rahulnanhore.model.WorkExperience;

import java.util.ArrayList;
import java.util.List;

public class WorkExperienceMapper {
    public static WorkExperienceResponse toWorkExperienceResponse(WorkExperience workExperience) {
        if (workExperience == null) return null;
        List<String> technologies = workExperience.getTechnologies() != null ? List.copyOf(workExperience.getTechnologies()) : List.of();

        return WorkExperienceResponse.builder()
                .id(workExperience.getId())
                .resumeId(workExperience.getResume().getId())
                .companyName(workExperience.getCompanyName())
                .companyLogoUrl(workExperience.getCompanyLogoUrl())
                .jobTitle(workExperience.getJobTitle())
                .employmentType(workExperience.getEmploymentType())
                .location(workExperience.getLocation())
                .startDate(workExperience.getStartDate())
                .endDate(workExperience.getEndDate())
                .isCurrentJob(workExperience.getIsCurrentJob())
                .description(workExperience.getDescription())
                .technologies(technologies)
                .displayOrder(workExperience.getDisplayOrder())
                .createdAt(workExperience.getCreatedAt())
                .updatedAt(workExperience.getUpdatedAt())
                .build();
    }
}
