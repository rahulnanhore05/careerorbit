package io.rahulnanhore.service.impl;

import io.rahulnanhore.dto.request.CertificateRequest;
import io.rahulnanhore.dto.response.CertificateResponse;
import io.rahulnanhore.mapper.CertificateMapper;
import io.rahulnanhore.model.Certificate;
import io.rahulnanhore.model.Resume;
import io.rahulnanhore.repository.CertificateRepository;
import io.rahulnanhore.service.CertificateService;
import io.rahulnanhore.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final ResumeService resumeService;

    @Override
    public CertificateResponse createCertificate(
            Long resumeId,
            Long candidateId,
            CertificateRequest certificateRequest) throws Exception {

        Resume resume = resumeService.getResumeEntity(resumeId);
        if (!resume.getCandidateId().equals(candidateId))
            throw new Exception("Resume not found");

        Certificate certificate = Certificate.builder()
                .resume(resume)
                .title(certificateRequest.getTitle())
                .description(certificateRequest.getDescription())
                .certificateUrl(certificateRequest.getCertificateUrl())
                .startDate(certificateRequest.getStartDate())
                .endDate(certificateRequest.getEndDate())
                .displayOrder(certificateRequest.getDisplayOrder() != null
                        ? certificateRequest.getDisplayOrder()
                        : 0)
                .build();

        return CertificateMapper.toCertificateResponse(
                certificateRepository.save(certificate)
        );
    }

    @Override
    public List<CertificateResponse> getCertificates(Long resumeId) {
        List<Certificate> certificates =
                certificateRepository.findByResume_IdOrderByDisplayOrderAsc(resumeId);

        return certificates.stream()
                .map(CertificateMapper::toCertificateResponse)
                .toList();
    }

    @Override
    public CertificateResponse updateCertificate(
            Long resumeId,
            Long candidateId,
            Long certificateId,
            CertificateRequest certificateRequest) throws Exception {

        Certificate certificate = certificateRepository
                .findByIdAndResume_Id(certificateId, resumeId)
                .orElseThrow(() -> new Exception("Certificate not found"));

        if (!certificate.getResume().getCandidateId().equals(candidateId))
            throw new Exception("Resume not found");

        if (StringUtils.hasText(certificateRequest.getTitle()))
            certificate.setTitle(certificateRequest.getTitle());

        if (StringUtils.hasText(certificateRequest.getDescription()))
            certificate.setDescription(certificateRequest.getDescription());

        if (StringUtils.hasText(certificateRequest.getCertificateUrl()))
            certificate.setCertificateUrl(certificateRequest.getCertificateUrl());

        if (certificateRequest.getStartDate() != null)
            certificate.setStartDate(certificateRequest.getStartDate());

        if (certificateRequest.getEndDate() != null)
            certificate.setEndDate(certificateRequest.getEndDate());

        if (certificateRequest.getDisplayOrder() != null)
            certificate.setDisplayOrder(certificateRequest.getDisplayOrder());

        return CertificateMapper.toCertificateResponse(
                certificateRepository.save(certificate)
        );
    }

    @Override
    public void deleteCertificate(
            Long resumeId,
            Long candidateId,
            Long certificateId) throws Exception {

        Certificate certificate = certificateRepository
                .findByIdAndResume_Id(certificateId, resumeId)
                .orElseThrow(() -> new Exception("Certificate not found"));

        if (!certificate.getResume().getCandidateId().equals(candidateId))
            throw new Exception("Resume not found");

        certificateRepository.delete(certificate);
    }

    @Override
    public Certificate getCertificateEntity(
            Long resumeId,
            Long certificateId) throws Exception {

        return certificateRepository
                .findByIdAndResume_Id(certificateId, resumeId)
                .orElseThrow(() -> new Exception("Certificate not found"));
    }
}