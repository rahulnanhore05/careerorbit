package io.rahulnanhore.service.impl;

import io.rahulnanhore.client.GeminiClient;
import io.rahulnanhore.payload.*;
import io.rahulnanhore.service.ResumeAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeAiServiceImpl implements ResumeAiService {

    private final GeminiClient geminiClient;

    String SYSTEM_PROMPT = """
            You are a senior resume writer and career coach with 15+ years of experience in the Indian tech job market.
            You specialize in ATS-optinized resynes, career coaching, and professional branding.
            Always be specific and results-oriented. Never use generic phrases Like "hard-working", "team player”, or "passionate.
            When asked for JSON, respond ONLY with valid JSON - no explanation, no markdown fences.
            """;

    @Override
    public AiTextResponse generateSummary(ResumeSummaryRequest request) {
        String workExperiences = request.getWorkExperiences() != null ?
                request.getWorkExperiences()
                        .stream()
                        .map(workExperienceInfo -> workExperienceInfo.getJobTitle() + " at "
                                                   + workExperienceInfo.getCompany()
                                                   + (workExperienceInfo.getDescription() != null ? ". Description: " + workExperienceInfo.getDescription() : ""))
                        .collect(Collectors.joining(";"))
                : "Not Provided";

        String educations = request.getEducations() != null ? request.getEducations()
                .stream()
                .map(educationInfo -> educationInfo.getDegree()
                                      + (educationInfo.getField() != null ? " in " + educationInfo.getField() : "")
                                      + (educationInfo.getInstitutionName() != null ? " from " + educationInfo.getInstitutionName() : ""))
                .collect(Collectors.joining(";")) : "Not Provided";

        String prompt = """
                Write a compelling professional summary for a resume.
                
                Candidate Profile:
                - Target Job Title: %s
                - Years of Experience: %d
                - Work Experience: %s
                - Key Skills: %s
                - Education: %s
                
                Write a 3-4 sentence professional summary that:
                1. Opens with seniority level and area of expertise
                2. Highlights 2-3 key achievements or strengths with impact
                3. Mentions specific technical skills relevant to the target role
                4. Ends with a value proposition or career goal
                
                Rules:
                - Write in first person (no "I" at the start)
                - Be specific and results-oriented
                - Keep it under 80 words
                - Make it ATS-friendly
                """.formatted(
                request.getTargetJobTitle() != null ? request.getTargetJobTitle() : "Not Provided",
                request.getYearsOfExperience() != null ? request.getYearsOfExperience() : 0,
                workExperiences,
                String.join(", ", request.getSkills()),
                educations
        );

        String generatedText = geminiClient.generateText(SYSTEM_PROMPT, prompt);
        return AiTextResponse.builder().content(generatedText).build();
    }

    @Override
    public WorkExperienceBulletsResponse generateWorkExperienceBullets(WorkExperienceBulletsRequest request) {
        String prompt = """
                Transform this work experience into powerful, ATS-friendly resume bullet points.
                
                Role: %s at %s
                Raw Description: %s
                Achievements/Hints: %s
                
                Generate exactly 4-5 bullet points that:
                1. Start with strong action verbs (Developed, Led, Implemented, Architected, Optimized, Reduced, Increased, Delivered, Built, Designed)
                2. Include quantifiable metrics where possible (percentages, numbers, time saved)
                3. Highlight business impact and technical achievements
                4. Are concise (under 20 words each)
                5. Are ATS-friendly with relevant keywords
                {
                "bullets": ["bullet point 1", "bullet point 2", "bullet point 3", "bullet point 4", “bullet point 5"]
                }
                """.formatted(
                request.getJobTitle(),
                (request.getCompany() != null) ? request.getCompany() : "the company",
                request.getRawDescription(),
                request.getAchievementsHint()
        );
        return geminiClient.generateJson(SYSTEM_PROMPT, prompt, WorkExperienceBulletsResponse.class);
    }

    @Override
    public CareerFeedbackResponse generateCareerFeedback(CareerFeedbackRequest request) {
        String prompt = """
                Analyze this resume and deliver an honest, actionable career feedback report.
                
                Target Job Title (if provided): %s
                Resume Content:
                %s
                
                {
                    "profileStrength”: 65,
                    “shortlistingIssues”: ["Reason 1 why recruiters are skipping this profile”, "Reason 2", “Reason 3"],
                    "improvements": [
                        { "area": "Skills | Summary | Experience | Education | Projects | General", "issue": "What specifically is weak or missing", "action": "Concrete action to should take to fix it", "priority": "HIGH | MEDIUM | LOW" }
                    ]
                    “targetJobs": [
                        { "jobTitle": "Recommended Job Title", “reason”: "Why this role suits the current profile", "skillMatch": "HIGH | MEDIUM | LOW" }
                    ],
                    “overallSummary": "2-3 sentences of honest, encouraging career advice"
                }
                
                Rules:
                - profileStrength: integer 0-100 reflecting overall job market readiness
                - shortlistingIssves: 3-5 candid reasons a recruiter would skip this resume
                - improvements: 4-6 items ordered by priority descending
                - targetJobs: 3-5 realistic job titles matching current skills and experience level
                - Be specific — mention actual skills, tools, or sections by name
                """.formatted(
                request.getTargetJobTitle() != null ? request.getTargetJobTitle() : "Not specified",
                request.getResumeContent()
        );
        return geminiClient.generateJson(SYSTEM_PROMPT, prompt, CareerFeedbackResponse.class);
    }

    @Override
    public ResumeImprovementResponse generateResumeImprovement(ResumeImprovementRequest request) {
        String prompt = """
                Analyze this resume and provide specific, actionable improvement suggestions.
                
                Target Job Title: %s
                Resume Content:
                %s
                {
                    “overallScore": 72,
                    "improvements": [
                        { "section": "Summgry or Experience or Skills or Education or General", "issue": "What is wrong or missing", suggestion: "Specific action to fix", priority: "HIGH | MEDIUM | LOW"}
                    ],
                    "strengths": ["what is already good about this resume"],
                    “summary”: "2-sentence overall assessment"
                }
                Provide 4-6 specific improvements. Score should be 0-100.
                """.formatted(
                request.getTargetJobTitle() != null ? request.getTargetJobTitle() : "Not specified",
                request.getResumeContent()
        );
        return geminiClient.generateJson(SYSTEM_PROMPT, prompt, ResumeImprovementResponse.class);
    }
}
