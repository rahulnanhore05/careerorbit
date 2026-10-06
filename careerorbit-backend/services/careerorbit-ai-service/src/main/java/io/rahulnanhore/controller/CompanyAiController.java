package io.rahulnanhore.controller;

import io.rahulnanhore.payload.AiTextResponse;
import io.rahulnanhore.payload.CompanyDescriptionRequest;
import io.rahulnanhore.payload.CompanyTaglineRequest;
import io.rahulnanhore.payload.CompanyTaglineResponse;
import io.rahulnanhore.service.CompanyAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ai/company")
@RequiredArgsConstructor
public class CompanyAiController {

    private final CompanyAiService companyAiService;

    @PostMapping("/describe")
    public ResponseEntity<AiTextResponse> generateCompanyDescription(@RequestBody CompanyDescriptionRequest req) {
        return ResponseEntity.ok(companyAiService.generateCompanyDescription(req));
    }

    @PostMapping("/taglines")
    public ResponseEntity<CompanyTaglineResponse> generateCompanyTaglines(@RequestBody CompanyTaglineRequest req) {
        return ResponseEntity.ok(companyAiService.generateCompanyTaglines(req));
    }
}
