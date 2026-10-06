package io.rahulnanhore.mapper;

import io.rahulnanhore.dto.response.LanguageResponse;
import io.rahulnanhore.model.Language;

public class LanguageMapper {
    public static LanguageResponse toLanguageResponse(Language language) {
        return LanguageResponse.builder()
                .id(language.getId())
                .name(language.getName())
                .proficiency(language.getProficiency())
                .displayOrder(language.getDisplayOrder())
                .createdAt(language.getCreatedAt())
                .updatedAt(language.getUpdatedAt())
                .build();
    }
}
