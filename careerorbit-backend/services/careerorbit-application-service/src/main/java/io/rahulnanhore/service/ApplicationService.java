package io.rahulnanhore.service;

import io.rahulnanhore.domain.ApplicationStatus;
import io.rahulnanhore.dto.request.ApplicationRequest;
import io.rahulnanhore.dto.response.ApplicationResponse;
import io.rahulnanhore.model.Application;
import io.rahulnanhore.payload.CompanyApplicationFilter;
import io.rahulnanhore.payload.UpdateApplicationStatusRequest;
import io.rahulnanhore.payload.WithdrawApplicationRequest;

import java.util.List;

public interface ApplicationService {
    ApplicationResponse createApplication(Long candidateId, ApplicationRequest applicationRequest) throws Exception;

    ApplicationResponse getApplicationById(Long id) throws Exception;

    List<ApplicationResponse> getMyApplications(Long candidateId);

    List<ApplicationResponse> getApplicationsForCompany(Long userId, CompanyApplicationFilter filter);

    List<ApplicationResponse> getApplicationForJob(Long jobId) throws Exception;

    ApplicationResponse updateStatus(Long applicationId, Long employerId, UpdateApplicationStatusRequest request) throws Exception;

    ApplicationResponse withdrawApplication(Long applicationId, Long candidateId, WithdrawApplicationRequest withdrawalRequest) throws Exception;

    ApplicationResponse toggleStar(Long applicationId, Long employerId) throws Exception;

    void deleteApplication(Long applicationId, Long candidateId) throws Exception;

    Application getApplicationEntity(Long applicationId) throws Exception;
}
