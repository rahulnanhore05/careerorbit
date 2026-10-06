package io.rahulnanhore.controller;

import io.rahulnanhore.dto.PersonalInfoDto;
import io.rahulnanhore.dto.request.ResumeRequest;
import io.rahulnanhore.dto.response.ResumeResponse;
import io.rahulnanhore.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<ResumeResponse> createResume(
            @RequestHeader("X-User-Id") Long candidateId,
            @Valid @RequestBody ResumeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(resumeService.createResume(candidateId, request));
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<ResumeResponse> getResumeById(
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long resumeId) throws Exception {
        return ResponseEntity.ok(resumeService.getResumeById(candidateId, resumeId));
    }

    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<ResumeResponse>> getAllMyResumes(
            @PathVariable Long candidateId) {
        return ResponseEntity.ok(resumeService.getAllMyResumes(candidateId));
    }

    @PutMapping("/{resumeId}/personal")
    public ResponseEntity<ResumeResponse> updatePersonalInfoResume(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long categoryId,
            @RequestBody PersonalInfoDto personalInfoDto) throws Exception {
        return ResponseEntity.ok(resumeService.updatePersonalInfoResume(resumeId, categoryId, personalInfoDto));
    }

    @PutMapping("/{resumeId}/summary")
    public ResponseEntity<ResumeResponse> updateSummary(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody String summary) throws Exception {
        return ResponseEntity.ok(resumeService.updateSummary(resumeId, candidateId, summary));
    }

    @PutMapping("/{resumeId}/default")
    public ResponseEntity<ResumeResponse> setDefaultResume(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        return ResponseEntity.ok(resumeService.setDefaultResume(resumeId, candidateId));
    }

    @DeleteMapping("/{resumeId}")
    public ResponseEntity<Void> deleteResume(
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long resumeId ) throws Exception {
        resumeService.deleteResume(candidateId, resumeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
