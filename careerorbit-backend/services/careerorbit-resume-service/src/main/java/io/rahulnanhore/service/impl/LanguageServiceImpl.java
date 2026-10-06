package io.rahulnanhore.service.impl;

import io.rahulnanhore.domain.LanguageProficiency;
import io.rahulnanhore.dto.request.LanguageRequest;
import io.rahulnanhore.dto.response.LanguageResponse;
import io.rahulnanhore.mapper.LanguageMapper;
import io.rahulnanhore.model.Language;
import io.rahulnanhore.model.Resume;
import io.rahulnanhore.repository.LanguageRepository;
import io.rahulnanhore.service.LanguageService;
import io.rahulnanhore.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private final ResumeService resumeService;

    @Override
    public LanguageResponse createLanguage(Long resumeId, Long candidateId, LanguageRequest languageRequest) throws Exception {
        Resume resume = resumeService.getResumeEntity(resumeId);
        if (!resume.getCandidateId().equals(candidateId)) throw new RuntimeException("Resume not found");

        Language language = Language.builder()
                .resume(resume)
                .name(languageRequest.getName())
                .proficiency(languageRequest.getProficiency() != null ? languageRequest.getProficiency() : LanguageProficiency.BASIC)
                .displayOrder(languageRequest.getDisplayOrder())
                .build();
        return LanguageMapper.toLanguageResponse(languageRepository.save(language));
    }

    @Override
    public List<LanguageResponse> getLanguages(Long resumeId) {
        return languageRepository.findByResume_IdOrderByDisplayOrderAsc(resumeId)
                .stream()
                .map(LanguageMapper::toLanguageResponse)
                .toList();
    }

    @Override
    public LanguageResponse updateLanguage(Long resumeId, Long candidateId, Long languageId, LanguageRequest languageRequest) throws Exception {
        Language language = languageRepository.findByIdAndResume_Id(languageId, resumeId);
        if (!language.getResume().getCandidateId().equals(candidateId)) throw new Exception("Resume not found");

        if (StringUtils.hasText(languageRequest.getName())) language.setName(languageRequest.getName());
        if (languageRequest.getProficiency() != null) language.setProficiency(languageRequest.getProficiency());
        if (languageRequest.getDisplayOrder() != null) language.setDisplayOrder(languageRequest.getDisplayOrder());

        return LanguageMapper.toLanguageResponse(languageRepository.save(language));
    }

    @Override
    public void deleteLanguage(Long resumeId, Long candidateId, Long languageId) throws Exception {
        Language language = languageRepository.findByIdAndResume_Id(languageId, resumeId);
        if (!language.getResume().getCandidateId().equals(candidateId)) throw new Exception("Resume not found");
        languageRepository.delete(language);
    }

    @Override
    public Language getLanguageEntity(Long resumeId, Long languageId) throws Exception {
        return languageRepository.findById(languageId).orElseThrow(() -> new Exception("Language not found"));
    }
}
