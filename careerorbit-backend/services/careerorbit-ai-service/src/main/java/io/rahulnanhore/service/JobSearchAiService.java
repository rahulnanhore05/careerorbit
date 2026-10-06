package io.rahulnanhore.service;

import io.rahulnanhore.payload.*;
import org.springframework.stereotype.Service;

public interface JobSearchAiService {
    SearchEnhanceResponse enhanceSearch(SearchEnhanceRequest req);
    JobMatchResponse calculateJobMatch(JobMatchRequest req);
    JobAlertSuggestResponse suggestJobAlertCriteria(JobAlertSuggestRequest req);
}
