package io.rahulnanhore.controller;

import io.rahulnanhore.payload.AiTextResponse;
import io.rahulnanhore.payload.JobDescriptionRequest;
import io.rahulnanhore.payload.SalaryRangeRequest;
import io.rahulnanhore.payload.SalaryRangeResponse;
import io.rahulnanhore.service.JobAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ai/job")
@RequiredArgsConstructor
public class JobAiController {
    private final JobAiService jobAiService;

    @PostMapping("/description")
    public ResponseEntity<AiTextResponse> generateJobDescription(JobDescriptionRequest req) {
        return ResponseEntity.ok(jobAiService.generateJobDescription(req));
    }

    @PostMapping("/requirements")
    public ResponseEntity<AiTextResponse> generateJobRequirements(String title, String category) {
        return ResponseEntity.ok(jobAiService.generateJobRequirements(title, category));
    }

    @PostMapping("/responsibilities")
    public ResponseEntity<AiTextResponse> generateJobResponsibilities(String title, String category) {
        return ResponseEntity.ok(jobAiService.generateJobResponsibilities(title, category));
    }

    @PostMapping("/benefits")
    public ResponseEntity<AiTextResponse> generateJobBenefits(String title, String category, String jobType) {
        return ResponseEntity.ok(jobAiService.generateJobBenefits(title, category, jobType));
    }

    @PostMapping("/skills-recommendation")
    public ResponseEntity<AiTextResponse> recommendSkillsForJob(String jobTitle, String description) {
        return ResponseEntity.ok(jobAiService.recommendSkillsForJob(jobTitle, description));
    }

    @PostMapping("/tags-recommendation")
    public ResponseEntity<AiTextResponse> recommendTagsForJob(String title, String description) {
        return ResponseEntity.ok(jobAiService.recommendTagsForJob(title, description));
    }

    @PostMapping("/salary-suggestion")
    public ResponseEntity<SalaryRangeResponse> suggestSalaryRange(SalaryRangeRequest req) {
        return ResponseEntity.ok(jobAiService.suggestSalaryRange(req));
    }
}
