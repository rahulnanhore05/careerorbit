package io.rahulnanhore.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import io.rahulnanhore.config.GeminiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class GeminiClient {

    private final GeminiProperties properties;
    private final Client genAiClient;
    private final ObjectMapper objectMapper;

    public String generateText(String systemInstruction, String prompt) {
       return callText(systemInstruction, prompt, properties.getMaxOutputTokens(), properties.getTemperature());
    }

    public <T> T generateJson(String systemInstruction, String prompt, Class<T> responseType) {
        return callJson(systemInstruction, prompt, responseType);
    }

    private String callText(String systemInstruction,
                            String prompt,
                            int maxTokens,
                            double temperature)
    {
        try {
            GenerateContentConfig config = buildConfig(systemInstruction, (float) temperature, maxTokens, false);
            GenerateContentResponse response = genAiClient.models.generateContent(
                    properties.getModel(),
                    prompt,
                    config
            );
            String text = response.text();
            return text;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private <T> T callJson(String systemInstruction,
                            String prompt,
                            Class<T> responseType)
    {
        try {
            GenerateContentConfig config = buildConfig(systemInstruction, 0.3f, properties.getMaxOutputTokens(), true);
            GenerateContentResponse response = genAiClient.models.generateContent(
                    properties.getModel(),
                    prompt,
                    config
            );
            String text = response.text();
            return objectMapper.readValue(text, responseType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private GenerateContentConfig buildConfig(String systemInstruction, float temperature, int maxTokens, boolean jsonMode) {
        GenerateContentConfig.Builder builder = GenerateContentConfig.builder()
                .temperature(temperature)
                .maxOutputTokens(maxTokens);

        if (StringUtils.hasText(systemInstruction)) {
            builder.systemInstruction(Content.fromParts(Part.fromText(systemInstruction)));
        }

        if (jsonMode) {
            builder.responseMimeType("application/json");
        }

        return builder.build();
    }

}
