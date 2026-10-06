package io.rahulnanhore.payload;

import io.rahulnanhore.domain.ApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateApplicationStatusRequest {
    @NotNull(message = "Status is required")
    private ApplicationStatus status;

    private String note;
}
