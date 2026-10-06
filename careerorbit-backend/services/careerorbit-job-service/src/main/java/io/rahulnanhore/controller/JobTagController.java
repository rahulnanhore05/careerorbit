package io.rahulnanhore.controller;

import io.rahulnanhore.dto.request.JobTagRequest;
import io.rahulnanhore.dto.response.JobTagResponse;
import io.rahulnanhore.service.JobTagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobs/tags")
public class JobTagController {

    private final JobTagService jobTagService;

    @PostMapping
    public ResponseEntity<JobTagResponse> createJobTag(@Valid @RequestBody JobTagRequest jobTagRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobTagService.createJobTag(jobTagRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobTagResponse> getJobTagById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobTagService.getJobTagById(id));
    }

    @GetMapping
    public ResponseEntity<List<JobTagResponse>> getAllJobTags() {
        return ResponseEntity.ok(jobTagService.getAllJobTags());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobTagResponse> updateJobTag(@PathVariable Long id, @RequestBody JobTagRequest jobTagRequest) throws Exception {
        return ResponseEntity.ok(jobTagService.updateJobTag(id, jobTagRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobTag(@PathVariable Long id) throws Exception {
        jobTagService.deleteJobTag(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
