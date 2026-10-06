package io.rahulnanhore.dto.response;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavedJobResponse {
    private Long id;
    private Long candidateId;
    private Long jobId;
    private LocalDateTime savedAt;
}
