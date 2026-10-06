package io.rahulnanhore.mapper;

import io.rahulnanhore.dto.response.CertificateResponse;
import io.rahulnanhore.model.Certificate;

public class CertificateMapper {
    public static CertificateResponse toCertificateResponse(Certificate certificate) {
        return CertificateResponse.builder()
                .id(certificate.getId())
                .title(certificate.getTitle())
                .description(certificate.getDescription())
                .certificateUrl(certificate.getCertificateUrl())
                .startDate(certificate.getStartDate())
                .endDate(certificate.getEndDate())
                .displayOrder(certificate.getDisplayOrder())
                .createdAt(certificate.getCreatedAt())
                .updatedAt(certificate.getUpdatedAt())
                .build();
    }
}
