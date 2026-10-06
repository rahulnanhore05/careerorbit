package io.rahulnanhore.controller;

import io.rahulnanhore.dto.request.SavedJobRequest;
import io.rahulnanhore.dto.response.SavedJobResponse;
import io.rahulnanhore.service.SavedJobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/preferences/savedjobs")
@RequiredArgsConstructor
public class SavedJobController {
    private final SavedJobService savedJobService;

    @PostMapping
    public ResponseEntity<SavedJobResponse> saveJob(
            @RequestHeader("X-User-Id") Long candidateId,
            @Valid @RequestBody SavedJobRequest savedJobRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(savedJobService.saveJob(candidateId, savedJobRequest));
    }

    @GetMapping("/candidates/{candidateId}")
    public ResponseEntity<List<SavedJobResponse>> getMySavedJobs(
            @RequestHeader("X-User-Id") Long candidateId){
        return ResponseEntity.ok(savedJobService.getSavedJobs(candidateId));
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> isSaved(
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestParam(name = "job") Long jobId
    ){
        return ResponseEntity.ok(savedJobService.isJobSaved(candidateId, jobId));
    }

    @DeleteMapping("/{savedJobId}")
    public  ResponseEntity<Void> deleteJob(
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long savedJobId
    ) throws Exception {
        savedJobService.unsaveJob(candidateId, savedJobId);
        return ResponseEntity.noContent().build();
    }

}
