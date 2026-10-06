package io.rahulnanhore.client;

import io.rahulnanhore.dto.response.ResumeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "CAREERORBIT-RESUME-SERVICE")
public interface ResumeClient {

    @GetMapping("api/v1/resumes/{resumeId}")
    ResumeResponse getResumeById(@RequestHeader("X-User-Id") Long candidateId, @PathVariable Long resumeId);
}
