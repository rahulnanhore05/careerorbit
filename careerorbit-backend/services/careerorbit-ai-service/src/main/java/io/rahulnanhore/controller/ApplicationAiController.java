package io.rahulnanhore.controller;

import io.rahulnanhore.payload.*;
import io.rahulnanhore.service.ApplicationAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
public class ApplicationAiController {
    private final ApplicationAiService applicationAiService;

    @PostMapping("/cover-letter")
    public ResponseEntity<AiTextResponse> generateCoverLetter(CoverLetterRequest req) {
        return ResponseEntity.ok(applicationAiService.generateCoverLetter(req));
    }

    @PostMapping("/screening-score")
    public ResponseEntity<ScreeningScoreResponse> scoreCandidate(ScreeningScoreRequest req) {
        return ResponseEntity.ok(applicationAiService.scoreCandidate(req));
    }

    @PostMapping("/skills-gap")
    public ResponseEntity<SkillsGapResponse> analyzeSkillsGap(SkillsGapRequest req) {
        return ResponseEntity.ok(applicationAiService.analyzeSkillsGap(req));
    }

    @PostMapping("/summarize-notes")
    public ResponseEntity<AiTextResponse> summarizeApplicationNotes(SummarizeNotesRequest req) {
        return ResponseEntity.ok(applicationAiService.summarizeApplicationNotes(req.getNotes()));
    }
}
