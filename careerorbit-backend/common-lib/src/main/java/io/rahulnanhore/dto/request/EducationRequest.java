package io.rahulnanhore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationRequest {

    @NotBlank(message = "Institution name is required")
    private String institutionName;

    @NotBlank(message = "Degree is required")
    private String degree;
    private String fieldOfStudy;
    private String grade;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    private LocalDate endDate;

    private Boolean isCurrentlyStudying;
    private String description;
    private Integer displayOrder;

}
