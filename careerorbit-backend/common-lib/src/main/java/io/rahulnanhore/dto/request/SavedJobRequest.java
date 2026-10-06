package io.rahulnanhore.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavedJobRequest {
    @NotNull(message = "Job ID is required")
    private Long jobId;
}
