package io.rahulnanhore.service;

import io.rahulnanhore.payload.*;
import org.springframework.stereotype.Service;

public interface ResumeAiService {
    AiTextResponse generateSummary(ResumeSummaryRequest request);
    WorkExperienceBulletsResponse generateWorkExperienceBullets(WorkExperienceBulletsRequest request);
    CareerFeedbackResponse generateCareerFeedback(CareerFeedbackRequest request);
    ResumeImprovementResponse generateResumeImprovement(ResumeImprovementRequest request);
}
