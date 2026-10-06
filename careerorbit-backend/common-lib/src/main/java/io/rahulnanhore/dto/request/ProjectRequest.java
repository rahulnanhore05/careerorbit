package io.rahulnanhore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectRequest {
    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private List<String> technologies;

    private String projectUrl;

    private String sourceCodeUrl;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean isCurrentlyWorking;

    private Integer displayOrder;
}
