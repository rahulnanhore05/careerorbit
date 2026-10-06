package io.rahulnanhore.service;

import io.rahulnanhore.dto.request.LanguageRequest;
import io.rahulnanhore.dto.response.LanguageResponse;
import io.rahulnanhore.model.Language;

import java.util.List;

public interface LanguageService {
    LanguageResponse createLanguage(Long resumeId, Long candidateId, LanguageRequest languageRequest) throws Exception;
    List<LanguageResponse> getLanguages(Long resumeId);
    LanguageResponse updateLanguage(Long resumeId, Long candidateId, Long languageId, LanguageRequest languageRequest) throws Exception;
    void deleteLanguage(Long resumeId, Long candidateId, Long languageId) throws Exception;
    Language getLanguageEntity(Long resumeId, Long languageId) throws Exception;
}
