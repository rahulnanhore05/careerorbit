package io.rahulnanhore.service;

import io.rahulnanhore.domain.CompanyStatus;
import io.rahulnanhore.domain.CompanyType;
import io.rahulnanhore.domain.IndustryType;
import io.rahulnanhore.dto.request.CompanyRequest;
import io.rahulnanhore.dto.response.CompanyResponse;
import io.rahulnanhore.model.Company;

import java.util.List;

public interface CompanyService {

    CompanyResponse createCompany(Long ownerId, CompanyRequest request) throws Exception;
    CompanyResponse getCompanyById(Long id) throws Exception;
    CompanyResponse getMyCompany(Long ownerId) throws Exception;
    List<CompanyResponse> getAllCompanies(CompanyType companyType,
                                          IndustryType industryType,
                                          CompanyStatus companyStatus) throws Exception;
    CompanyResponse updateCompany(Long ownerId, Long companyId, CompanyRequest request) throws Exception;
    CompanyResponse verifyCompany(Long companyId) throws Exception;
    void deleteCompany(Long ownerId, Long companyId) throws Exception;
    CompanyResponse deactivateCompany(Long companyId) throws Exception;

    Company getCompanyEntityById(Long companyId) throws Exception; //Internal service call
}
