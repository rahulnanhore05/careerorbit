package io.rahulnanhore.controller;

import io.rahulnanhore.dto.request.ApplicationRequest;
import io.rahulnanhore.dto.response.ApplicationResponse;
import io.rahulnanhore.payload.CompanyApplicationFilter;
import io.rahulnanhore.payload.UpdateApplicationStatusRequest;
import io.rahulnanhore.payload.WithdrawApplicationRequest;
import io.rahulnanhore.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationResponse> createApplication(
            @RequestHeader("X-User-Id") Long candidateId,
            @Valid @RequestBody ApplicationRequest applicationRequest
    ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationService.createApplication(candidateId, applicationRequest));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationResponse> getApplicationById(
            @PathVariable Long applicationId
    ) throws Exception {
        return ResponseEntity.ok(applicationService.getApplicationById(applicationId));
    }

    @GetMapping("/my")
    public ResponseEntity<List<ApplicationResponse>> getMyApplications(
            @RequestHeader("X-User-Id") Long candidateId
    ) {
        return ResponseEntity.ok(applicationService.getMyApplications(candidateId));
    }

    @GetMapping("/jobs/{jobId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsForJob(
            @PathVariable Long jobId
    ) throws Exception {
        return ResponseEntity.ok(applicationService.getApplicationForJob(jobId));
    }

    @GetMapping("/company")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsForCompany(
            @RequestHeader("X-User-Id") Long userId,
            @RequestBody CompanyApplicationFilter filter
    ) {
        return ResponseEntity.ok(applicationService.getApplicationsForCompany(userId, filter));
    }

    @PutMapping("/{applicationId}/status")
    public ResponseEntity<ApplicationResponse> updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestHeader("X-User-Id") Long employerId,
            @Valid @RequestBody UpdateApplicationStatusRequest request
    ) throws Exception {
        return ResponseEntity.ok(applicationService.updateStatus(applicationId, employerId, request));
    }

    @PutMapping("/{applicationId}/withdraw")
    public ResponseEntity<ApplicationResponse> withdrawApplication(
            @PathVariable Long applicationId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody WithdrawApplicationRequest request
    ) throws Exception {
        return ResponseEntity.ok(applicationService.withdrawApplication(applicationId, candidateId, request));
    }

    @PutMapping("/{applicationId}/star")
    public ResponseEntity<ApplicationResponse> toggleStar(
            @PathVariable Long applicationId,
            @RequestHeader("X-User-Id") Long employerId
    ) throws Exception {
        return ResponseEntity.ok(applicationService.toggleStar(applicationId, employerId));
    }

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<Void> deleteApplication(
            @PathVariable Long applicationId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {
        applicationService.deleteApplication(applicationId, candidateId);
        return ResponseEntity.noContent().build();
    }
}
