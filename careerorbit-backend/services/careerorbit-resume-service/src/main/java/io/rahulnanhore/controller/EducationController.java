package io.rahulnanhore.controller;

import io.rahulnanhore.dto.request.EducationRequest;
import io.rahulnanhore.dto.response.EducationResponse;
import io.rahulnanhore.service.EducationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resumes/{resumeId}/educations")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    @PostMapping
    public ResponseEntity<EducationResponse> createEducation(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @Valid @RequestBody EducationRequest request) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(educationService.createEducation(resumeId, candidateId, request));
    }

    @GetMapping
    public ResponseEntity<List<EducationResponse>> getEducations(
            @PathVariable Long resumeId) {

        return ResponseEntity.ok(educationService.getEducations(resumeId));
    }

    @PutMapping("/{educationId}")
    public ResponseEntity<EducationResponse> updateEducation(
            @PathVariable Long resumeId,
            @PathVariable Long educationId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody EducationRequest request) throws Exception {

        return ResponseEntity.ok(
                educationService.updateEducation(
                        educationId,
                        resumeId,
                        candidateId,
                        request
                )
        );
    }

    @DeleteMapping("/{educationId}")
    public ResponseEntity<Void> deleteEducation(
            @PathVariable Long resumeId,
            @PathVariable Long educationId,
            @RequestHeader("X-User-Id") Long candidateId) throws Exception {

        educationService.deleteEducation(
                educationId,
                resumeId,
                candidateId
        );

        return ResponseEntity.noContent().build();
    }
}