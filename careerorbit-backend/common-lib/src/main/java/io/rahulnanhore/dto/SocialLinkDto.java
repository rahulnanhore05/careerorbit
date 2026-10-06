package io.rahulnanhore.dto;


import io.rahulnanhore.domain.SocialPlatform;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SocialLinkDto {

    private SocialPlatform platform;
    private String url;
}
