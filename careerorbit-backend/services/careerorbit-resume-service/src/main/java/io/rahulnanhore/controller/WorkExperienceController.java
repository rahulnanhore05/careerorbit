package io.rahulnanhore.controller;

import io.rahulnanhore.dto.request.WorkExperienceRequest;
import io.rahulnanhore.dto.response.WorkExperienceResponse;
import io.rahulnanhore.service.WorkExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resumes/{resumeId}/experiences")
@RequiredArgsConstructor
public class WorkExperienceController {

    private final WorkExperienceService workExperienceService;

    @PostMapping
    public ResponseEntity<WorkExperienceResponse> createWorkExperience(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @Valid @RequestBody WorkExperienceRequest request) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(workExperienceService.createWorkExperience(resumeId, candidateId, request));
    }

    @GetMapping
    public ResponseEntity<List<WorkExperienceResponse>> getWorkExperiences(
            @PathVariable Long resumeId) {
        return ResponseEntity.ok(workExperienceService.getWorkExperiences(resumeId));
    }

    @PutMapping("/{workExperienceId}")
    public ResponseEntity<WorkExperienceResponse> updateWorkExperience(
            @PathVariable Long resumeId,
            @PathVariable Long workExperienceId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody WorkExperienceRequest request) throws Exception {

        return ResponseEntity.ok(
                workExperienceService.updateWorkExperience(
                        resumeId,
                        workExperienceId,
                        candidateId,
                        request
                )
        );
    }

    @DeleteMapping("/{workExperienceId}")
    public ResponseEntity<Void> deleteWorkExperience(
            @PathVariable Long resumeId,
            @PathVariable Long workExperienceId,
            @RequestHeader("X-User-Id") Long candidateId) throws Exception {

        workExperienceService.deleteWorkExperience(
                resumeId,
                candidateId,
                workExperienceId
        );

        return ResponseEntity.noContent().build();
    }
}