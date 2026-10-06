package io.rahulnanhore.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalInfoDto {

    private String firstName;
    private String lastName;

    private String headline;

    private String email;
    private String phone;
    private String city;
    private String country;

    private String linkedin;
    private String github;
    private String portfolio;
    private String website;
}
