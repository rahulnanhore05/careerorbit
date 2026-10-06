package io.rahulnanhore.client;

import io.rahulnanhore.dto.response.JobResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CAREERORBIT-JOB-SERVICE")
public interface JobClient {

    @GetMapping("/api/v1/jobs/{jobId}")
    JobResponse getJobById(@PathVariable Long jobId);
}
