package io.rahulnanhore.service.impl;

import io.rahulnanhore.mapper.EducationMapper;
import io.rahulnanhore.dto.request.EducationRequest;
import io.rahulnanhore.dto.response.EducationResponse;
import io.rahulnanhore.model.Education;
import io.rahulnanhore.model.Resume;
import io.rahulnanhore.repository.EducationRepository;
import io.rahulnanhore.service.EducationService;
import io.rahulnanhore.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final ResumeService resumeService;

    @Override
    public EducationResponse createEducation(Long resumeId, Long candidateId, EducationRequest request) throws Exception {
        Resume resume = resumeService.getResumeEntity(resumeId);
        if (!resume.getCandidateId().equals(candidateId)) throw new Exception("Resume not found");

        Education education = Education.builder()
                .resume(resume)
                .institutionName(request.getInstitutionName())
                .degree(request.getDegree())
                .fieldOfStudy(request.getFieldOfStudy())
                .grade(request.getGrade())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isCurrentlyStudying(request.getIsCurrentlyStudying())
                .description(request.getDescription())
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .build();
        return EducationMapper.toEducationResponse(educationRepository.save(education));
    }

    @Override
    public List<EducationResponse> getEducations(Long resumeId) {
        List<Education> educations = educationRepository.findByResume_IdOrderByDisplayOrderAsc(resumeId);
        return educations.stream().map(EducationMapper::toEducationResponse).toList();
    }

    @Override
    public EducationResponse updateEducation(Long educationId, Long resumeId, Long candidateId, EducationRequest request) throws Exception {

        Education education = educationRepository.findByIdAndResume_Id(educationId, resumeId).orElseThrow(() -> new Exception("Education not found"));
        if (!education.getResume().getCandidateId().equals(candidateId)) throw new Exception("Resume not found");

        if (StringUtils.hasText(request.getInstitutionName()))
            education.setInstitutionName(request.getInstitutionName());

        if (StringUtils.hasText(request.getDegree()))
            education.setDegree(request.getDegree());

        if (StringUtils.hasText(request.getFieldOfStudy()))
            education.setFieldOfStudy(request.getFieldOfStudy());

        if (StringUtils.hasText(request.getGrade()))
            education.setGrade(request.getGrade());

        if (request.getStartDate() != null)
            education.setStartDate(request.getStartDate());

        if (request.getEndDate() != null)
            education.setEndDate(request.getEndDate());

        if (request.getIsCurrentlyStudying() != null)
            education.setIsCurrentlyStudying(request.getIsCurrentlyStudying());

        if (StringUtils.hasText(request.getDescription()))
            education.setDescription(request.getDescription());

        if (request.getDisplayOrder() != null)
            education.setDisplayOrder(request.getDisplayOrder());

        return EducationMapper.toEducationResponse(educationRepository.save(education));
    }

    @Override
    public void deleteEducation(Long educationId, Long resumeId, Long candidateId) throws Exception {
        Education education = educationRepository.findByIdAndResume_Id(educationId, resumeId).orElseThrow(() -> new Exception("Education not found"));
        if (!education.getResume().getCandidateId().equals(candidateId)) throw new Exception("Resume not found");
        educationRepository.delete(education);
    }

    @Override
    public Education getEducationEntity(Long educationId) throws Exception {
        return educationRepository.findById(educationId).orElseThrow(() -> new Exception("Education not found"));
    }
}
