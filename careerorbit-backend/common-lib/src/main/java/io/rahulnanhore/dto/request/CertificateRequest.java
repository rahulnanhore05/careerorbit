package io.rahulnanhore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CertificateRequest {
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    private String certificateUrl;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer displayOrder;
}
