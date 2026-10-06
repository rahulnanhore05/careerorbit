package io.rahulnanhore.mapper;

import io.rahulnanhore.dto.SocialLinkDto;
import io.rahulnanhore.dto.response.CompanyResponse;
import io.rahulnanhore.model.Company;
import io.rahulnanhore.model.SocialLink;

import java.util.ArrayList;
import java.util.List;

public class CompanyMapper {

    public static CompanyResponse toCompanyResponse(Company company){
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .email(company.getEmail())
                .phone(company.getPhone())
                .slug(company.getSlug())
                .registrationNumber(company.getRegistrationNumber())
                .verified(company.isVerified())
                .ownerId(company.getOwnerId())
                .tagline(company.getTagline())
                .description(company.getDescription())
                .logoUrl(company.getLogoUrl())
                .coverImageUrl(company.getCoverImageUrl())
                .websiteUrl(company.getWebsiteUrl())
                .foundedYear(company.getFoundedYear())
                .companySize(company.getCompanySize())
                .companyType(company.getCompanyType())
                .industryType(company.getIndustryType())
                .status(company.getStatus())
                .createdAt(company.getCreatedAt())
                .updatedAt(company.getUpdatedAt())
                .socialLinks(toSocialLinksDto(company.getSocialLinks()))
                .build();
    }

    public static List<SocialLinkDto> toSocialLinksDto(List<SocialLink> socialLinks){
        if(socialLinks == null || socialLinks.isEmpty()){
            return new ArrayList<SocialLinkDto>();
        }

        return socialLinks.stream()
                .map(link ->
                        SocialLinkDto.builder()
                                .platform(link.getPlatform())
                                .url(link.getUrl())
                                .build())
                .toList();
    }
}
