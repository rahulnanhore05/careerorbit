package io.rahulnanhore.service.impl;

import io.rahulnanhore.client.CompanyClient;
import io.rahulnanhore.client.JobClient;
import io.rahulnanhore.client.ResumeClient;
import io.rahulnanhore.client.UserClient;
import io.rahulnanhore.domain.ApplicationStatus;
import io.rahulnanhore.dto.request.ApplicationRequest;
import io.rahulnanhore.dto.response.*;
import io.rahulnanhore.event.ApplicationEventPublisher;
import io.rahulnanhore.mapper.ApplicationMapper;
import io.rahulnanhore.model.Application;
import io.rahulnanhore.payload.CompanyApplicationFilter;
import io.rahulnanhore.payload.UpdateApplicationStatusRequest;
import io.rahulnanhore.payload.WithdrawApplicationRequest;
import io.rahulnanhore.repository.ApplicationNoteRepository;
import io.rahulnanhore.repository.ApplicationRepository;
import io.rahulnanhore.repository.ApplicationSpecification;
import io.rahulnanhore.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationNoteRepository applicationNoteRepository;
    private final JobClient jobClient;
    private final ResumeClient resumeClient;
    private final CompanyClient companyClient;
    private final UserClient userClient;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public ApplicationResponse createApplication(Long candidateId, ApplicationRequest applicationRequest) throws Exception {
        if (applicationRepository.existsByCandidateIdAndJobId(candidateId, applicationRequest.getJobId()))
            throw new Exception("Application already exists");

        JobResponse job = jobClient.getJobById(applicationRequest.getJobId());
        Long companyId = job.getCompany().getId();
        Long employerId = job.getEmployerId();

        resumeClient.getResumeById(candidateId, applicationRequest.getResumeId());

        Application application = Application.builder()
                .candidateId(candidateId)
                .jobId(applicationRequest.getJobId())
                .companyId(companyId)
                .employerId(employerId)
                .resumeId(applicationRequest.getResumeId())
                .coverLetter(applicationRequest.getCoverLetter())
                .expectedSalary(applicationRequest.getExpectedSalary())
                .availableFrom(applicationRequest.getAvailableFrom())
                .build();
        return buildApplicationResponse(applicationRepository.save(application));
    }

    @Override
    public ApplicationResponse getApplicationById(Long id) throws Exception {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new Exception("Application not found"));
        return buildApplicationResponse(application);
    }

    @Override
    public List<ApplicationResponse> getMyApplications(Long candidateId) {
        return applicationRepository.findByCandidateId(candidateId).stream()
                .map(this::buildApplicationResponse)
                .toList();
    }

    @Override
    public List<ApplicationResponse> getApplicationsForCompany(Long userId, CompanyApplicationFilter filter) {

        Long companyId = companyClient.getMyCompany(userId).getId();

        Specification<Application> spec = ApplicationSpecification.forCompanyWithFilters(companyId, filter.getJobId(), filter.getStatus(), filter.getIsStarred(), filter.getAiShortListStatus(), filter.getMinAiScore());
        return applicationRepository.findAll(spec, buildSort(filter.getSortBy())).stream()
                .map(this::buildApplicationResponse)
                .toList();
    }

    @Override
    public List<ApplicationResponse> getApplicationForJob(Long jobId) throws Exception {

        JobResponse job = jobClient.getJobById(jobId);
        if (!job.getEmployerId().equals(jobId)) throw new Exception("Job not found");

        return applicationRepository.findByJobId(jobId).stream()
                .map(this::buildApplicationResponse)
                .toList();
    }

    @Override
    public ApplicationResponse updateStatus(Long applicationId, Long employerId, UpdateApplicationStatusRequest request) throws Exception {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new Exception("Application not found"));
        if (!application.getEmployerId().equals(employerId)) throw new Exception("Application not found");

        if (request.getStatus() == ApplicationStatus.WITHDRAWN) {
            throw new Exception("Candidate withdrew the application");
        }
        if (application.getStatus() == request.getStatus()) {
            throw new Exception("Application is already in status: " + request.getStatus());
        }
        ApplicationStatus oldStatus = application.getStatus();
        application.setStatus(request.getStatus());
        applicationRepository.save(application);
        eventPublisher.publishStatusChanged(application, request.getNote());
        return buildApplicationResponse(application);
    }

    @Override
    public ApplicationResponse withdrawApplication(Long applicationId, Long candidateId, WithdrawApplicationRequest withdrawalRequest) throws Exception {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new Exception("Application not found"));
        if (!application.getCandidateId().equals(candidateId)) throw new Exception("Application not found");

        application.setWithdrawnAt(LocalDate.now());
        application.setWithdrawalReason(withdrawalRequest.getReason());
        Application savedApplication = applicationRepository.save(application);

        return buildApplicationResponse(savedApplication);
    }

    @Override
    public ApplicationResponse toggleStar(Long applicationId, Long employerId) throws Exception {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new Exception("Application not found"));
        if (!application.getEmployerId().equals(employerId)) throw new Exception("Application not found");
        if (application.getStatus().equals(ApplicationStatus.WITHDRAWN))
            throw new Exception("Application is withdrawn");

        application.setIsStarred(!application.getIsStarred());
        Application savedApplication = applicationRepository.save(application);
        return buildApplicationResponse(savedApplication);
    }

    @Override
    public void deleteApplication(Long applicationId, Long candidateId) throws Exception {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new Exception("Application not found"));
        if (!application.getCandidateId().equals(candidateId)) throw new Exception("Application not found");
        applicationRepository.delete(application);
    }

    @Override
    public Application getApplicationEntity(Long applicationId) throws Exception {
        return applicationRepository.findById(applicationId).orElseThrow(() -> new Exception("Application not found"));
    }

    private ApplicationResponse buildApplicationResponse(Application application) {

        List<ApplicationNoteResponse> notes = applicationNoteRepository.findAllByApplication_Id(application.getId()).stream()
                .map(ApplicationMapper::toApplicationNoteResponse)
                .toList();

        UserResponse user = userClient.getUserById(application.getCandidateId());
        JobResponse job = jobClient.getJobById(application.getJobId());
        CompanyResponse company = companyClient.getCompanyById(application.getCompanyId());

        return ApplicationMapper.toApplicationResponse(application, notes, user, job, company);
    }

    private Sort buildSort(String sortBy) {
        if (StringUtils.hasText(sortBy) && "AI_SCORE_DESC".equals(sortBy)) {
            return Sort.by(Sort.Order.desc("aiScore").with(Sort.NullHandling.NULLS_LAST));
        }
        if (StringUtils.hasText(sortBy) && "AI_SCORE_ASC".equals(sortBy)) {
            return Sort.by(Sort.Order.asc("aiScore").with(Sort.NullHandling.NULLS_LAST));
        }
        return Sort.by(Sort.Direction.DESC, "createdAt");
    }
}
