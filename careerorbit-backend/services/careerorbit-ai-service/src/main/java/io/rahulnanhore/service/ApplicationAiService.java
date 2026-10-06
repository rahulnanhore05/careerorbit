package io.rahulnanhore.service;

import io.rahulnanhore.payload.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ApplicationAiService {
    AiTextResponse generateCoverLetter(CoverLetterRequest req);
    ScreeningScoreResponse scoreCandidate(ScreeningScoreRequest req);
    SkillsGapResponse analyzeSkillsGap(SkillsGapRequest req);
    AiTextResponse summarizeApplicationNotes(List<String> notes);
}
