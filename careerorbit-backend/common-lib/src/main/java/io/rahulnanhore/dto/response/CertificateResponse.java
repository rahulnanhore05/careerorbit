package io.rahulnanhore.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CertificateResponse {
    private Long id;
    private String title;
    private String description;
    private String certificateUrl;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer displayOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
