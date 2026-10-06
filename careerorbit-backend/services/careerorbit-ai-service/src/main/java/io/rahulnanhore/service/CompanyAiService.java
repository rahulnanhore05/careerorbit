package io.rahulnanhore.service;

import io.rahulnanhore.payload.AiTextResponse;
import io.rahulnanhore.payload.CompanyDescriptionRequest;
import io.rahulnanhore.payload.CompanyTaglineRequest;
import io.rahulnanhore.payload.CompanyTaglineResponse;

import org.springframework.stereotype.Service;

public interface CompanyAiService {
    AiTextResponse generateCompanyDescription(CompanyDescriptionRequest req);
    CompanyTaglineResponse generateCompanyTaglines(CompanyTaglineRequest req);
}
