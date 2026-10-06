package io.rahulnanhore.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationResponse {
    private Long id;
    private String institutionName;
    private String degree;

    private String fieldOfStudy;
    private String grade;
    private LocalDate startDate;
    private LocalDate endDate;

    private Boolean isCurrentlyStudying;
    private String description;
    private Integer displayOrder;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
