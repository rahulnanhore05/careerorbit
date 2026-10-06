package io.rahulnanhore.model.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalInfo {

    private String firstName;
    private String lastName;

    private String headline;

    private String email;
    private String phone;
    private String city;
    private String country;

    private String linkedInUrl;
    private String githubUrl;
    private String portfolioUrl;
    private String websiteUrl;
}
