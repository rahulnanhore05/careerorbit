package io.rahulnanhore.dto.request;

import io.rahulnanhore.domain.CompanySize;
import io.rahulnanhore.domain.CompanyStatus;
import io.rahulnanhore.domain.CompanyType;
import io.rahulnanhore.domain.IndustryType;
import io.rahulnanhore.dto.SocialLinkDto;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CompanyRequest {

    @NotNull(message = "Company name is required")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    private String phone;

    private String tagline;
    private String description;
    private String logoUrl;
    private String coverImageUrl;
    private String registrationNumber;

    @Pattern(regexp = "^https://www\\.[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Invalid website URL")
    private String websiteUrl;

    @Min(value = 1800, message = "Invalid founded year")
    @Max(value = 2100, message = "Invalid founded year")
    private Integer foundedYear;

    @NotNull(message = "Company size is required")
    private CompanySize companySize;

    @NotNull(message = "Company type is required")
    private CompanyType companyType;

    @NotNull(message = "Industry type is required")
    private IndustryType industryType;

    @NotNull(message = "Company status is required")
    private CompanyStatus status;

    private List<SocialLinkDto> socialLinks;
}
