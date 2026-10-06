package io.rahulnanhore.dto.response;

import io.rahulnanhore.domain.LanguageProficiency;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LanguageResponse {
    private Long id;
    private String name;
    private LanguageProficiency proficiency;
    private Integer displayOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
