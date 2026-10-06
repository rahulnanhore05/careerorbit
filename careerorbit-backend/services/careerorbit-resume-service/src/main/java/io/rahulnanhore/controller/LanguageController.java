package io.rahulnanhore.controller;

import io.rahulnanhore.dto.request.LanguageRequest;
import io.rahulnanhore.dto.response.LanguageResponse;
import io.rahulnanhore.service.LanguageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resumes/{resumeId}/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping
    public ResponseEntity<LanguageResponse> createLanguage(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @Valid @RequestBody LanguageRequest languageRequest) throws Exception {

        LanguageResponse response =
                languageService.createLanguage(resumeId, candidateId, languageRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LanguageResponse>> getLanguages(
            @PathVariable Long resumeId) {

        List<LanguageResponse> languages = languageService.getLanguages(resumeId);
        return ResponseEntity.ok(languages);
    }

    @PutMapping("/{languageId}")
    public ResponseEntity<LanguageResponse> updateLanguage(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long languageId,
            @RequestBody LanguageRequest languageRequest) throws Exception {

        LanguageResponse response = languageService.updateLanguage(
                resumeId,
                candidateId,
                languageId,
                languageRequest
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{languageId}")
    public ResponseEntity<Void> deleteLanguage(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long languageId) throws Exception {

        languageService.deleteLanguage(resumeId, candidateId, languageId);
        return ResponseEntity.noContent().build();
    }
}