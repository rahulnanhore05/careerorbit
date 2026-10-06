package io.rahulnanhore.service.impl;

import io.rahulnanhore.mapper.WorkExperienceMapper;
import io.rahulnanhore.dto.request.WorkExperienceRequest;
import io.rahulnanhore.dto.response.WorkExperienceResponse;
import io.rahulnanhore.model.Resume;
import io.rahulnanhore.model.WorkExperience;
import io.rahulnanhore.repository.WorkExperienceRepository;
import io.rahulnanhore.service.ResumeService;
import io.rahulnanhore.service.WorkExperienceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;
    private final ResumeService resumeService;

    @Override
    public WorkExperienceResponse createWorkExperience(Long resumeId, Long candidateId, WorkExperienceRequest request) throws Exception {
        Resume resume = resumeService.getResumeEntity(resumeId);

        if(!resume.getCandidateId().equals(candidateId)) throw new Exception("Resume not found");

        WorkExperience workExperience = WorkExperience.builder()
                .resume(resume)
                .companyName(request.getCompanyName())
                .companyLogoUrl(request.getCompanyLogoUrl())
                .jobTitle(request.getJobTitle())
                .employmentType(request.getEmploymentType())
                .location(request.getLocation())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isCurrentJob(Boolean.TRUE.equals(request.getIsCurrentJob()))
                .description(request.getDescription())
                .technologies(request.getTechnologies() != null ? request.getTechnologies() : new ArrayList<>())
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .build();
        return WorkExperienceMapper.toWorkExperienceResponse(workExperienceRepository.save(workExperience));
    }

    @Override
    public List<WorkExperienceResponse> getWorkExperiences(Long resumeId) {
        List<WorkExperience> workExperiences = workExperienceRepository.findByResume_IdOrderByDisplayOrder(resumeId);
        return workExperiences.stream().map(WorkExperienceMapper::toWorkExperienceResponse).toList();
    }

    @Override
    public WorkExperienceResponse updateWorkExperience(Long resumeId, Long workExperienceId, Long candidateId, WorkExperienceRequest request) throws Exception {
        WorkExperience workExperience = workExperienceRepository.findByIdAndResume_Id(workExperienceId, resumeId).orElseThrow(() -> new Exception("Work Experience not found"));
        if(!workExperience.getResume().getCandidateId().equals(candidateId)) throw new Exception("Resume not found");

        if (StringUtils.hasText(request.getCompanyName()))
            workExperience.setCompanyName(request.getCompanyName());

        if (StringUtils.hasText(request.getCompanyLogoUrl()))
            workExperience.setCompanyLogoUrl(request.getCompanyLogoUrl());

        if (StringUtils.hasText(request.getJobTitle()))
            workExperience.setJobTitle(request.getJobTitle());

        if (request.getEmploymentType() != null)
            workExperience.setEmploymentType(request.getEmploymentType());

        if (StringUtils.hasText(request.getLocation()))
            workExperience.setLocation(request.getLocation());

        if (request.getStartDate() != null)
            workExperience.setStartDate(request.getStartDate());

        if (request.getEndDate() != null)
            workExperience.setEndDate(request.getEndDate());

        if (request.getIsCurrentJob() != null)
            workExperience.setIsCurrentJob(request.getIsCurrentJob());

        if (StringUtils.hasText(request.getDescription()))
            workExperience.setDescription(request.getDescription());

        if (request.getTechnologies() != null)
            workExperience.setTechnologies(request.getTechnologies());

        if (request.getDisplayOrder() != null)
            workExperience.setDisplayOrder(request.getDisplayOrder());

        return WorkExperienceMapper.toWorkExperienceResponse(workExperienceRepository.save(workExperience));
    }

    @Override
    public void deleteWorkExperience(Long resumeId, Long candidateId, Long workExperienceId) throws Exception {
        WorkExperience workExperience = workExperienceRepository.findByIdAndResume_Id(workExperienceId, resumeId).orElseThrow(() -> new Exception("Work experience not found"));
        if (!workExperience.getResume().getCandidateId().equals(candidateId)) throw new Exception("Resume not found");
        workExperienceRepository.delete(workExperience);
    }

    @Override
    public WorkExperience getWorkExperienceEntity(Long workExperienceId) throws Exception {
        return workExperienceRepository.findById(workExperienceId)
                .orElseThrow(() -> new Exception("Work experience not found"));
    }
}
