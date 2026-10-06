package io.rahulnanhore.controller;

import io.rahulnanhore.domain.CompanyStatus;
import io.rahulnanhore.domain.CompanyType;
import io.rahulnanhore.domain.IndustryType;
import io.rahulnanhore.dto.request.CompanyRequest;
import io.rahulnanhore.dto.response.CompanyResponse;
import io.rahulnanhore.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(
            @RequestHeader("X-User-Id") Long ownerId,
            @RequestBody @Valid CompanyRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.createCompany(ownerId, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(
            @PathVariable("id") Long companyId) throws Exception {
        return ResponseEntity.ok(companyService.getCompanyById(companyId));
    }

    @GetMapping("/my")
    public ResponseEntity<CompanyResponse> getMyCompany(
            @RequestHeader("X-User-Id") Long ownId
    ) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getMyCompany(ownId));
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAllCompanies(
            @RequestParam(value = "company", required = false)CompanyType companyType,
            @RequestParam(value = "industry", required = false) IndustryType industryType,
            @RequestParam(value = "status", required = false) CompanyStatus status
            ) throws Exception {
        return ResponseEntity.ok(companyService.getAllCompanies(companyType, industryType, status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable("id") Long companyId,
            @RequestHeader("X-User-Id") Long ownerId,
            @RequestBody @Valid CompanyRequest request) throws Exception {
        return ResponseEntity.ok(companyService.updateCompany(ownerId, companyId, request));
    }

    @PatchMapping("/{id}/verify")
    public ResponseEntity<CompanyResponse> verifyCompany(
            @PathVariable("id") Long companyId) throws Exception {
        return ResponseEntity.ok(companyService.verifyCompany(companyId));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<CompanyResponse> deactivateCompany(
            @PathVariable("id") Long companyId) throws Exception {
        return ResponseEntity.ok(companyService.deactivateCompany(companyId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(
            @RequestHeader("X-User-Id") Long ownerId,
            @PathVariable("id") Long companyId) throws Exception {
        companyService.deleteCompany(ownerId, companyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
