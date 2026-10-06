package io.rahulnanhore.dto.response;

import io.rahulnanhore.domain.CompanySize;
import io.rahulnanhore.domain.CompanyStatus;
import io.rahulnanhore.domain.CompanyType;
import io.rahulnanhore.domain.IndustryType;
import io.rahulnanhore.dto.SocialLinkDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CompanyResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String slug;
    private String registrationNumber;
    private Long ownerId;
    private String tagline;
    private String description;
    private String logoUrl;
    private String coverImageUrl;
    private String websiteUrl;
    private Integer foundedYear;
    private boolean verified;

    private CompanySize companySize;
    private CompanyType companyType;
    private IndustryType industryType;
    private CompanyStatus status;
    private List<SocialLinkDto> socialLinks = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
