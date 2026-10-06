package io.rahulnanhore.service.impl;

import io.rahulnanhore.domain.CompanyStatus;
import io.rahulnanhore.domain.CompanyType;
import io.rahulnanhore.domain.IndustryType;
import io.rahulnanhore.dto.request.CompanyRequest;
import io.rahulnanhore.dto.SocialLinkDto;
import io.rahulnanhore.dto.response.CompanyResponse;
import io.rahulnanhore.mapper.CompanyMapper;
import io.rahulnanhore.model.Company;
import io.rahulnanhore.model.SocialLink;
import io.rahulnanhore.repository.CompanyRepository;
import io.rahulnanhore.service.CompanyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyResponse createCompany(Long ownerId, CompanyRequest request) throws Exception {

        if (companyRepository.existsByOwnerId(ownerId)) {
            throw new Exception("Company already exists");
        }

        if (companyRepository.existsByName(request.getName())) {
            throw new Exception("Company name already exists");
        }

        if (request.getRegistrationNumber() != null && !request.getRegistrationNumber().isBlank() && companyRepository.existsByRegistrationNumber(request.getRegistrationNumber())) {
            throw new Exception("Company registration number already exists");
        }

        String slug = generateUniqueSlug(request.getName());

        Company company = Company.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .slug(slug)
                .registrationNumber(request.getRegistrationNumber())
                .ownerId(ownerId)
                .tagline(request.getTagline())
                .description(request.getDescription())
                .logoUrl(request.getLogoUrl())
                .coverImageUrl(request.getCoverImageUrl())
                .websiteUrl(request.getWebsiteUrl())
                .foundedYear(request.getFoundedYear())
                .companySize(request.getCompanySize())
                .companyType(request.getCompanyType())
                .industryType(request.getIndustryType())
                .status(request.getStatus())
                .socialLinks(mapSocialLinks(request.getSocialLinks()))
                .build();

        return CompanyMapper.toCompanyResponse(companyRepository.save(company));
    }

    @Override
    public CompanyResponse getCompanyById(Long id) throws Exception {
        Company company = companyRepository.findById(id).orElseThrow(() -> new Exception("Company not found"));
        return CompanyMapper.toCompanyResponse(company);
    }

    @Override
    public CompanyResponse getMyCompany(Long ownerId) throws Exception {
        Company company = companyRepository.findByOwnerId(ownerId).orElseThrow(() -> new Exception("Company not found"));
        return CompanyMapper.toCompanyResponse(company);
    }

    @Override
    public List<CompanyResponse> getAllCompanies(CompanyType companyType, IndustryType industryType, CompanyStatus companyStatus) throws Exception {

        List<Company> companies = companyRepository.findByFilters(companyType, industryType, companyStatus);
        return companies.stream().map(CompanyMapper::toCompanyResponse).toList();
    }

    @Override
    public CompanyResponse updateCompany(Long ownerId, Long companyId, CompanyRequest request) throws Exception {

        Company company = companyRepository.findById(companyId).orElseThrow(() -> new Exception("Company not found"));

        String requestName = request.getName();
        if (StringUtils.hasText(requestName)
                && !requestName.equals(company.getName())
                && companyRepository.existsByName(requestName)) {
            throw new Exception("Company name already exists");
        }

        String requestRegistrationNumber = request.getRegistrationNumber();
        if (StringUtils.hasText(requestRegistrationNumber)
                && !requestRegistrationNumber.equals(company.getRegistrationNumber())
                && companyRepository.existsByRegistrationNumber(requestRegistrationNumber)) {
            throw new Exception("Company registration number already exists");
        }

        if (StringUtils.hasText(requestName)) company.setName(requestName);
        if (StringUtils.hasText(request.getEmail())) company.setEmail(request.getEmail());
        if (StringUtils.hasText(request.getPhone())) company.setPhone(request.getPhone());
        if (StringUtils.hasText(request.getTagline())) company.setTagline(request.getTagline());
        if (StringUtils.hasText(request.getDescription())) company.setDescription(request.getDescription());
        if (StringUtils.hasText(requestRegistrationNumber)) company.setRegistrationNumber(requestRegistrationNumber);
        if (StringUtils.hasText(request.getLogoUrl())) company.setLogoUrl(request.getLogoUrl());
        if (StringUtils.hasText(request.getWebsiteUrl())) company.setWebsiteUrl(request.getWebsiteUrl());
        if (request.getFoundedYear() != null) company.setFoundedYear(request.getFoundedYear());
        if (request.getCompanySize() != null) company.setCompanySize(request.getCompanySize());
        if (request.getCompanyType() != null) company.setCompanyType(request.getCompanyType());
        if (request.getIndustryType() != null) company.setIndustryType(request.getIndustryType());
        if (request.getStatus() != null) company.setStatus(request.getStatus());
        if (request.getSocialLinks() != null) company.setSocialLinks(mapSocialLinks(request.getSocialLinks()));

        return CompanyMapper.toCompanyResponse(companyRepository.save(company));
    }

    @Override
    public CompanyResponse verifyCompany(Long companyId) throws Exception {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new Exception("Company not found"));
        company.setVerified(true);
        company.setStatus(CompanyStatus.ACTIVE);
        return CompanyMapper.toCompanyResponse(companyRepository.save(company));
    }

    @Override
    public void deleteCompany(Long ownerId, Long companyId) throws Exception {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new Exception("Company not found"));
        if (!company.getOwnerId().equals(ownerId)) {
            throw new Exception("Unauthorized access");
        }
        companyRepository.delete(company);
    }

    @Override
    public CompanyResponse deactivateCompany(Long companyId) throws Exception {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new Exception("Company not found"));
        company.setStatus(CompanyStatus.SUSPENDED);
        Company savedCompany = companyRepository.save(company);
        return CompanyMapper.toCompanyResponse(savedCompany);

    }

    @Override
    public Company getCompanyEntityById(Long companyId) throws Exception {
        return companyRepository.findById(companyId).orElseThrow(() -> new Exception("Company not found"));
    }

    private String generateUniqueSlug(String name) {

        String slug = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("[\\s-]+", "-");

        if (!companyRepository.existsBySlug(slug)) {
            return slug;
        }

        int counter = 1;
        while (companyRepository.existsBySlug(slug + "-" + counter)) {
            counter++;
        }

        return slug + "-" + counter;
    }

    private List<SocialLink> mapSocialLinks(List<SocialLinkDto> socialLinks) {

        if (socialLinks == null || socialLinks.isEmpty()) {
            return new ArrayList<SocialLink>();
        }

        return socialLinks.stream()
                .map(link -> SocialLink.builder()
                        .platform(link.getPlatform())
                        .url(link.getUrl())
                        .build())
                .toList();
    }
}
