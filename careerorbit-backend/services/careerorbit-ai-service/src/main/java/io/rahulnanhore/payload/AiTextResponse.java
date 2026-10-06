package io.rahulnanhore.payload;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AiTextResponse {
    private String content;

    @Builder.Default
    private LocalDateTime generatedAt = LocalDateTime.now();
}
