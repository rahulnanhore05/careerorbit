package io.rahulnanhore.mapper;

import io.rahulnanhore.dto.response.JobTagResponse;
import io.rahulnanhore.model.JobTag;

public class JobTagMapper {

    public static JobTagResponse toJobTagResponse(JobTag tag){
        return JobTagResponse.builder()
                .id(tag.getId())
                .name(tag.getName())
                .slug(tag.getSlug())
                .createdAt(tag.getCreatedAt())
                .updatedAt(tag.getUpdatedAt())
                .build();
    }
}
