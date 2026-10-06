package io.rahulnanhore.controller;

import io.rahulnanhore.dto.request.ApplicationNoteRequest;
import io.rahulnanhore.dto.response.ApplicationNoteResponse;
import io.rahulnanhore.service.ApplicationNoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications/{applicationId}/notes")
@RequiredArgsConstructor
public class ApplicationNoteController {
    private final ApplicationNoteService applicationNoteService;

    @PostMapping
    public ResponseEntity<ApplicationNoteResponse> createNote(
            @PathVariable Long applicationId,
            @RequestHeader("X-User-Id") Long employerId,
            @Valid @RequestBody ApplicationNoteRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationNoteService.createNote(applicationId, employerId, request));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationNoteResponse>> getNotesByApplication(@PathVariable Long applicationId) {
        return ResponseEntity.ok(applicationNoteService.getNotesByApplication(applicationId));
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<ApplicationNoteResponse> getNote(
            @PathVariable Long applicationId,
            @PathVariable Long noteId,
            @RequestHeader("X-User-Id") Long employerId) throws Exception {
        applicationNoteService.deleteNote(applicationId, noteId, employerId);
        return ResponseEntity.notFound().build();
    }
}
