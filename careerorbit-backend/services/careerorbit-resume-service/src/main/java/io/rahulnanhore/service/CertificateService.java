package io.rahulnanhore.service;

import io.rahulnanhore.dto.request.CertificateRequest;
import io.rahulnanhore.dto.response.CertificateResponse;
import io.rahulnanhore.model.Certificate;

import java.util.List;

public interface CertificateService {
    CertificateResponse createCertificate(Long resumeId, Long candidateId, CertificateRequest certificateRequest) throws Exception;
    List<CertificateResponse> getCertificates(Long resumeId);
    CertificateResponse updateCertificate(Long resumeId, Long candidateId, Long certificateId, CertificateRequest certificateRequest) throws Exception;
    void deleteCertificate(Long resumeId, Long candidateId, Long certificateId) throws Exception;
    Certificate getCertificateEntity(Long resumeId, Long certificateId) throws Exception;
}
