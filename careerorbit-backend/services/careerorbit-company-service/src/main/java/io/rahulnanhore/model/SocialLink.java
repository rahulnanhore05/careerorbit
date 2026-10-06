package io.rahulnanhore.model;

import io.rahulnanhore.domain.SocialPlatform;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialLink {

    private SocialPlatform platform;
    private String url;
}
