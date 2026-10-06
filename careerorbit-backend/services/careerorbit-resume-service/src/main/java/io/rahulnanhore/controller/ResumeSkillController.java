package io.rahulnanhore.controller;

import io.rahulnanhore.dto.request.ResumeSkillRequest;
import io.rahulnanhore.dto.response.ResumeSkillResponse;
import io.rahulnanhore.service.ResumeSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resumes/{resumeId}/skills")
@RequiredArgsConstructor
public class ResumeSkillController {

    private final ResumeSkillService resumeSkillService;

    @PostMapping
    public ResponseEntity<ResumeSkillResponse> createResumeSkill(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @Valid @RequestBody ResumeSkillRequest request) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(resumeSkillService.createResumeSkill(resumeId, candidateId, request));
    }

    @GetMapping
    public ResponseEntity<List<ResumeSkillResponse>> getSkills(
            @PathVariable Long resumeId) {
        return ResponseEntity.ok(resumeSkillService.getSKills(resumeId));
    }

    @PutMapping("/{skillId}")
    public ResponseEntity<ResumeSkillResponse> updateResumeSkill(
            @PathVariable Long resumeId,
            @PathVariable Long skillId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody ResumeSkillRequest request) throws Exception {

        return ResponseEntity.ok(
                resumeSkillService.updateResumeSkill(
                        skillId,
                        resumeId,
                        candidateId,
                        request
                )
        );
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<Void> deleteResumeSkill(
            @PathVariable Long resumeId,
            @PathVariable Long skillId,
            @RequestHeader("X-User-Id") Long candidateId) throws Exception {

        resumeSkillService.deleteResumeSkill(
                skillId,
                resumeId,
                candidateId
        );

        return ResponseEntity.noContent().build();
    }
}