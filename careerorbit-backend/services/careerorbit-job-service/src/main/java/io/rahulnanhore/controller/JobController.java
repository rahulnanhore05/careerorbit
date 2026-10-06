package io.rahulnanhore.controller;

import io.rahulnanhore.dto.request.JobRequest;
import io.rahulnanhore.dto.response.JobResponse;
import io.rahulnanhore.payload.JobSearchRequest;
import io.rahulnanhore.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<JobResponse> createJob(
            @RequestHeader("X-User-Id") Long employerId,
            @Valid @RequestBody JobRequest jobRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.createJob(employerId, jobRequest));
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable Long jobId) throws Exception {
        return ResponseEntity.ok(jobService.getJobById(jobId));
    }

    @PostMapping("/search")
    public ResponseEntity<List<JobResponse>> getJobs(@RequestBody JobSearchRequest jobSearchRequest) {
        return ResponseEntity.ok(jobService.getJobs(jobSearchRequest));
    }

    @GetMapping("/companies/{companyId}")
    public ResponseEntity<List<JobResponse>> getJobsByCompany(@PathVariable Long companyId) throws Exception {
        return ResponseEntity.ok(jobService.getJobsByCompany(companyId));
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable Long jobId, @RequestHeader("X-User-Id") Long employerId, @RequestBody JobRequest jobRequest) throws Exception {
        return ResponseEntity.ok(jobService.updateJob(jobId, employerId, jobRequest));
    }

    @PatchMapping("/{jobId}/publish")
    public ResponseEntity<JobResponse> publishJob(@PathVariable Long jobId, @RequestHeader("X-User-Id") Long employerId) throws Exception {
        return ResponseEntity.ok(jobService.publishJob(jobId, employerId));
    }

    @PatchMapping("/{jobId}/close")
    public ResponseEntity<JobResponse> closeJob(@PathVariable Long jobId, @RequestHeader("X-User-Id") Long employerId) throws Exception {
        return ResponseEntity.ok(jobService.closeJob(jobId, employerId));
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long jobId, @RequestHeader("X-User-Id") Long employerId) throws Exception {
        jobService.deleteJob(jobId, employerId);
        return ResponseEntity.ok().build();
    }
}
