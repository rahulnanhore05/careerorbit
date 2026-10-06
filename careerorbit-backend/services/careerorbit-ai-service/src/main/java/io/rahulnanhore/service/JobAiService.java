package io.rahulnanhore.service;

import io.rahulnanhore.payload.AiTextResponse;
import io.rahulnanhore.payload.JobDescriptionRequest;
import io.rahulnanhore.payload.SalaryRangeRequest;
import io.rahulnanhore.payload.SalaryRangeResponse;
import org.springframework.stereotype.Service;

public interface JobAiService {
    AiTextResponse generateJobDescription(JobDescriptionRequest req);
    AiTextResponse generateJobRequirements(String title, String category);
    SalaryRangeResponse suggestSalaryRange(SalaryRangeRequest req);
    AiTextResponse generateJobResponsibilities(String title, String category);
    AiTextResponse generateJobBenefits(String title, String category, String jobType);
    AiTextResponse recommendSkillsForJob(String jobTitle, String description);
    AiTextResponse recommendTagsForJob(String title, String description);
}
