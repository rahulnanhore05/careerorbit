package io.rahulnanhore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationNoteRequest {
    @NotBlank(message = "Content cannot be blank")
    @Size(min = 1, max = 2000, message = "Note can not exceed 2000 characters")
    private String content;
}
