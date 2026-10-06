package io.rahulnanhore.mapper;

import io.rahulnanhore.dto.response.ProjectResponse;
import io.rahulnanhore.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectMapper {
    public static ProjectResponse toProjectResponse(Project project){

        List<String> technologies = project.getTechnologies() != null ? List.copyOf(project.getTechnologies()) : List.of();
        return ProjectResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .technologies(technologies)
                .projectUrl(project.getProjectUrl())
                .sourceCodeUrl(project.getSourceCodeUrl())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .isCurrentlyWorking(project.getIsCurrentlyWorking())
                .displayOrder(project.getDisplayOrder())
                .createdAt(project.getCreatedAt())
                .updatedAt(project.getUpdatedAt())
                .build();
    }
}
