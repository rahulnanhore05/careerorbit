package io.rahulnanhore.service.impl;

import io.rahulnanhore.client.GeminiClient;
import io.rahulnanhore.payload.AiTextResponse;
import io.rahulnanhore.payload.CompanyDescriptionRequest;
import io.rahulnanhore.payload.CompanyTaglineRequest;
import io.rahulnanhore.payload.CompanyTaglineResponse;
import io.rahulnanhore.service.CompanyAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyAiServiceImpl implements CompanyAiService {

    private final GeminiClient geminiClient;

    private static final String SYSTEM = """
            You are an expert employer branding copywriter specializing in company profiles for job portals.
            You write authentic, inspiring content that appeals to job seekers without corporate jargon.
            Write in a warm, human tone that reflects real company culture and values.
            When asked for JSON, respond ONLY with valid JSON — no explanation, no markdown fences.
            """;


    @Override
    public AiTextResponse generateCompanyDescription(CompanyDescriptionRequest req) {
        String prompt = """
                Write a compelling and authentic company "About Us" description for a job portal profile.
                
                Company Details:
                - Name: %s
                - Industry: %s
                - Company Type: %s
                - Company Size: %s
                - Tagline: %s
                - Additional Context: %s
                
                Write 2-3 engaging paragraphs that:
                1. Open with what the company does and its mission/vision
                2. Highlight the culture, team environment, and values
                3. Describe growth opportunities and what makes it a great place to work
                
                Rules:
                - Maximum 200 words
                - Write in third person
                - Don't start with "We are" or "Our company"
                - Make it inspiring for job seekers
                """.formatted(
                req.getName(),
                req.getIndustry() != null ? req.getIndustry() : "Technology",
                req.getCompanyType() != null ? req.getCompanyType() : "Startup",
                req.getSize() != null ? req.getSize() : "50-200 employees",
                req.getTagline() != null ? req.getTagline() : "Not provided",
                req.getAdditionalContext() != null ? req.getAdditionalContext() : "None"
        );

        return AiTextResponse.builder()
                .content(geminiClient.generateText(SYSTEM, prompt))
                .build();
    }


    @Override
    public CompanyTaglineResponse generateCompanyTaglines(CompanyTaglineRequest req) {
        String prompt = """
                Generate 3 short, memorable, and inspiring company taglines.
                
                Company Details:
                - Name: %s
                - Industry: %s
                - Description: %s
                
                Rules for each tagline:
                - Maximum 8 words
                - Must be unique and memorable
                - Should reflect the company's identity
                - Avoid generic phrases like "We are the best" or "Your trusted partner"
                - Should appeal to job seekers and clients alike
                
                { "taglines": ["tagline 1", "tagline 2", "tagline 3"] }
                """.formatted(
                req.getName(),
                req.getIndustry() != null ? req.getIndustry() : "Technology",
                req.getDescription() != null ? req.getDescription() : "Not provided"
        );

        return geminiClient.generateJson(SYSTEM, prompt, CompanyTaglineResponse.class);
    }
}
