package io.rahulnanhore.mapper;

import io.rahulnanhore.dto.response.JobCategoryResponse;
import io.rahulnanhore.model.JobCategory;

import java.util.List;

public class JobCategoryMapper {
    public static JobCategoryResponse toJobCategoryResponse(JobCategory category, boolean includeChildren){

        List<JobCategoryResponse> subCategories = null;
        if (includeChildren){
            subCategories = category.getSubCategories()
                    .stream()
                    .map(jobCategory -> toJobCategoryResponse(jobCategory, false))
                    .toList();
        }

        return JobCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .slug(category.getSlug())
                .description(category.getDescription())
                .iconUrl(category.getIconUrl())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .parent(category.getParent() != null ? category.getParent().getName() : null)
                .subCategories(subCategories)
                .active(category.getActive())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}
