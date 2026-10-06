package io.rahulnanhore.service.impl;

import io.rahulnanhore.mapper.ResumeMapper;
import io.rahulnanhore.dto.PersonalInfoDto;
import io.rahulnanhore.dto.request.ResumeRequest;
import io.rahulnanhore.dto.response.ResumeResponse;
import io.rahulnanhore.model.Resume;
import io.rahulnanhore.model.embeddable.PersonalInfo;
import io.rahulnanhore.repository.ResumeRepository;
import io.rahulnanhore.service.ResumeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Override
    public ResumeResponse createResume(Long candidateId, ResumeRequest request) {

        if (Boolean.TRUE.equals(request.getIsDefault())) {
            resumeRepository.findByCandidateIdAndIsDefaultTrue(candidateId).ifPresent(resume -> {
                resume.setIsDefault(false);
                resumeRepository.save(resume);
            });
        }

        Resume resume = Resume.builder()
                .title(request.getTitle())
                .candidateId(candidateId)
                .resumeTemplate(request.getTemplate())
                .visibility(request.getVisibility())
                .isDefault(Boolean.TRUE.equals(request.getIsDefault()))
                .build();
        Resume savedResume = resumeRepository.save(resume);
        return buildResumeResponse(savedResume);
    }

    @Override
    public ResumeResponse getResumeById(Long candidateId, Long resumeId) throws Exception {

        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new Exception("Resume not found"));
        if (!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("Resume not found");
        }
        return buildResumeResponse(resume);
    }

    @Override
    public List<ResumeResponse> getAllMyResumes(Long candidateId) {

        return resumeRepository.findByCandidateId(candidateId)
                .stream()
                .map(this::buildResumeResponse)
                .toList();
    }

    @Override
    public ResumeResponse updatePersonalInfoResume(Long resumeId, Long candidateId, PersonalInfoDto personalInfoDto) throws Exception {

        Resume resume = resumeRepository.findByIdAndCandidateId(resumeId, candidateId).orElseThrow(() -> new Exception("Resume not found"));
        PersonalInfo personalInfo = resume.getPersonalInfo() != null ? resume.getPersonalInfo() : new PersonalInfo();
        if (StringUtils.hasText(personalInfoDto.getFirstName()))
            personalInfo.setFirstName(personalInfoDto.getFirstName());

        if (StringUtils.hasText(personalInfoDto.getLastName()))
            personalInfo.setLastName(personalInfoDto.getLastName());

        if (StringUtils.hasText(personalInfoDto.getHeadline()))
            personalInfo.setHeadline(personalInfoDto.getHeadline());

        if (StringUtils.hasText(personalInfoDto.getEmail()))
            personalInfo.setEmail(personalInfoDto.getEmail());

        if (StringUtils.hasText(personalInfoDto.getPhone()))
            personalInfo.setPhone(personalInfoDto.getPhone());

        if (StringUtils.hasText(personalInfoDto.getCity()))
            personalInfo.setCity(personalInfoDto.getCity());

        if (StringUtils.hasText(personalInfoDto.getCountry()))
            personalInfo.setCountry(personalInfoDto.getCountry());

        if (StringUtils.hasText(personalInfoDto.getLinkedin()))
            personalInfo.setLinkedInUrl(personalInfoDto.getLinkedin());

        if (StringUtils.hasText(personalInfoDto.getGithub()))
            personalInfo.setGithubUrl(personalInfoDto.getGithub());

        if (StringUtils.hasText(personalInfoDto.getPortfolio()))
            personalInfo.setPortfolioUrl(personalInfoDto.getPortfolio());

        if (StringUtils.hasText(personalInfoDto.getWebsite()))
            personalInfo.setWebsiteUrl(personalInfoDto.getWebsite());

        resume.setPersonalInfo(personalInfo);
        Resume savedResume = resumeRepository.save(resume);
        return buildResumeResponse(savedResume);
    }

    @Override
    public ResumeResponse updateSummary(Long resumeId, Long candidateId, String summary) throws Exception {

        Resume resume = resumeRepository.findByIdAndCandidateId(resumeId, candidateId).orElseThrow(() -> new Exception("Resume not found"));
        if (StringUtils.hasText(summary))
            resume.setSummary(summary);
        Resume savedResume = resumeRepository.save(resume);
        return buildResumeResponse(savedResume);
    }

    @Override
    public ResumeResponse setDefaultResume(Long resumeId, Long candidateId) throws Exception {
        Resume resume = resumeRepository.findByIdAndCandidateId(resumeId, candidateId).orElseThrow(() -> new Exception("Resume not found"));
        if (Boolean.TRUE.equals(resume.getIsDefault())) {
            resumeRepository.findByCandidateIdAndIsDefaultTrue(candidateId).ifPresent(defaultResume -> {
                defaultResume.setIsDefault(false);
                resumeRepository.save(defaultResume);
            });
        }
        resume.setIsDefault(true);
        Resume savedResume = resumeRepository.save(resume);
        return buildResumeResponse(savedResume);
    }

    @Override
    public void deleteResume(Long candidateId, Long resumeId) throws Exception {
        Resume resume = resumeRepository.findByIdAndCandidateId(resumeId, candidateId).orElseThrow(() -> new Exception("Resume not found"));
        resumeRepository.delete(resume);
    }

    @Override
    public Resume getResumeEntity(Long resumeId) throws Exception {
        return resumeRepository.findById(resumeId).orElseThrow(() -> new Exception("Resume not found"));
    }

    private ResumeResponse buildResumeResponse(Resume resume) {
        return ResumeMapper.toResumeResponse(resume);
    }
}
