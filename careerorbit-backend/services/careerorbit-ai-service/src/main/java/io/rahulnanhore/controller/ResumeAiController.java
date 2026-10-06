package io.rahulnanhore.controller;

import io.rahulnanhore.payload.*;
import io.rahulnanhore.service.ResumeAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ai/resume")
@RequiredArgsConstructor
public class ResumeAiController {
    private final ResumeAiService resumeAiService;

    @PostMapping("/summary")
    public ResponseEntity<AiTextResponse> generateSummary(ResumeSummaryRequest request) {
        return ResponseEntity.ok(resumeAiService.generateSummary(request));
    }

    @PostMapping("/experience-bullets")
    public ResponseEntity<WorkExperienceBulletsResponse> generateWorkExperienceBullets(WorkExperienceBulletsRequest request) {
        return ResponseEntity.ok(resumeAiService.generateWorkExperienceBullets(request));
    }

    @PostMapping("/career-feedback")
    public ResponseEntity<CareerFeedbackResponse> generateCareerFeedback(CareerFeedbackRequest request) {
        return ResponseEntity.ok(resumeAiService.generateCareerFeedback(request));
    }

    @PostMapping("/improvement")
    public ResponseEntity<ResumeImprovementResponse> generateResumeImprovement(ResumeImprovementRequest request) {
        return ResponseEntity.ok(resumeAiService.generateResumeImprovement(request));
    }
}
