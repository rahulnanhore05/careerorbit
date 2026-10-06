package io.rahulnanhore.controller;

import io.rahulnanhore.payload.*;
import io.rahulnanhore.service.JobSearchAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ai/search")
@RequiredArgsConstructor
public class JobSearchAiController {
    private final JobSearchAiService jobSearchAiService;

    @PostMapping("/enhance")
    public ResponseEntity<SearchEnhanceResponse> enhanceSearch(SearchEnhanceRequest req) {
        return ResponseEntity.ok(jobSearchAiService.enhanceSearch(req));
    }

    @PostMapping("/job-match")
    public ResponseEntity<JobMatchResponse> calculateJobMatch(@RequestBody JobMatchRequest req) {
        return ResponseEntity.ok(jobSearchAiService.calculateJobMatch(req));
    }

    @PostMapping("/alert-suggestion")
    public ResponseEntity<JobAlertSuggestResponse> suggestJobAlertCriteria(JobAlertSuggestRequest req) {
        return ResponseEntity.ok(jobSearchAiService.suggestJobAlertCriteria(req));
    }
}
