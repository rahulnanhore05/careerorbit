package io.rahulnanhore.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CareerFeedbackRequest {
    @NotBlank(message = "Resume content is required")
    private String resumeContent;
    private String targetJobTitle;
}
