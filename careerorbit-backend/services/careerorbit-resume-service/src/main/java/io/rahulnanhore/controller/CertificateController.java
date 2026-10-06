package io.rahulnanhore.controller;

import io.rahulnanhore.dto.request.CertificateRequest;
import io.rahulnanhore.dto.response.CertificateResponse;
import io.rahulnanhore.service.CertificateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resumes/{resumeId}/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @PostMapping
    public ResponseEntity<CertificateResponse> createCertificate(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @Valid @RequestBody CertificateRequest certificateRequest) throws Exception {

        CertificateResponse response =
                certificateService.createCertificate(resumeId, candidateId, certificateRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CertificateResponse>> getCertificates(
            @PathVariable Long resumeId) {

        List<CertificateResponse> certificates =
                certificateService.getCertificates(resumeId);

        return ResponseEntity.ok(certificates);
    }

    @PutMapping("/{certificateId}")
    public ResponseEntity<CertificateResponse> updateCertificate(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long certificateId,
            @RequestBody CertificateRequest certificateRequest) throws Exception {

        CertificateResponse response = certificateService.updateCertificate(
                resumeId,
                candidateId,
                certificateId,
                certificateRequest
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{certificateId}")
    public ResponseEntity<Void> deleteCertificate(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long certificateId) throws Exception {

        certificateService.deleteCertificate(resumeId, candidateId, certificateId);
        return ResponseEntity.noContent().build();
    }
}