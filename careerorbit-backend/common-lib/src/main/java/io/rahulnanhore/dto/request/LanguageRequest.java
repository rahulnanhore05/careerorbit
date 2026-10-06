package io.rahulnanhore.dto.request;

import io.rahulnanhore.domain.LanguageProficiency;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LanguageRequest {

    @NotBlank(message = "Name is required")
    private String name;
    private LanguageProficiency proficiency;
    private Integer displayOrder;
}
