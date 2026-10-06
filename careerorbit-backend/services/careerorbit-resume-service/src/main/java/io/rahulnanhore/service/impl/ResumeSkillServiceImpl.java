package io.rahulnanhore.service.impl;

import io.rahulnanhore.mapper.ResumeSkillMapper;
import io.rahulnanhore.domain.ProficiencyLevel;
import io.rahulnanhore.dto.request.ResumeSkillRequest;
import io.rahulnanhore.dto.response.ResumeSkillResponse;
import io.rahulnanhore.model.Resume;
import io.rahulnanhore.model.ResumeSkill;
import io.rahulnanhore.repository.ResumeSkillRepository;
import io.rahulnanhore.service.ResumeService;
import io.rahulnanhore.service.ResumeSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeSkillServiceImpl implements ResumeSkillService {

    private final ResumeSkillRepository resumeSkillRepository;
    private final ResumeService resumeService;

    @Override
    public ResumeSkillResponse createResumeSkill(Long resumeId, Long candidateId, ResumeSkillRequest request) throws Exception {
        Resume resume = resumeService.getResumeEntity(resumeId);

        if(!resume.getCandidateId().equals(candidateId)) throw new Exception("Resume not found");

        ResumeSkill skill = ResumeSkill.builder()
                .resume(resume)
                .skillName(request.getSkillName())
                .proficiencyLevel(request.getProficiencyLevel() != null ? request.getProficiencyLevel() : ProficiencyLevel.BEGINNER)
                .yearsOfExperience(request.getYearsOfExperience() != null ? request.getYearsOfExperience() : 0)
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .build();
        return ResumeSkillMapper.toResumeSkillResponse(resumeSkillRepository.save(skill));
    }

    @Override
    public List<ResumeSkillResponse> getSKills(Long resumeId) {
        List<ResumeSkill> resumeSkills = resumeSkillRepository.findByResume_IdOrderByDisplayOrderAsc(resumeId);
        return resumeSkills.stream().map(ResumeSkillMapper::toResumeSkillResponse).toList();
    }

    @Override
    public ResumeSkillResponse updateResumeSkill(Long skillId, Long resumeId, Long candidateId, ResumeSkillRequest request) throws Exception {
        ResumeSkill skill = resumeSkillRepository.findByIdAndResume_Id(skillId, resumeId).orElseThrow(() -> new Exception("Skill not found"));
        if(!skill.getResume().getCandidateId().equals(candidateId)) throw new Exception("Resume not found");

        if(StringUtils.hasText(request.getSkillName()))
            skill.setSkillName(request.getSkillName());

        if(request.getProficiencyLevel() != null)
            skill.setProficiencyLevel(request.getProficiencyLevel());

        if (request.getYearsOfExperience() != null)
            skill.setYearsOfExperience(request.getYearsOfExperience());

        if (request.getDisplayOrder() != null)
            skill.setDisplayOrder(request.getDisplayOrder());


        return ResumeSkillMapper.toResumeSkillResponse(resumeSkillRepository.save(skill));
    }

    @Override
    public void deleteResumeSkill(Long skillId, Long resumeId, Long candidateId) throws Exception {
        ResumeSkill skill = resumeSkillRepository.findByIdAndResume_Id(skillId, resumeId).orElseThrow(() -> new Exception("Skill not found"));
        if(!skill.getResume().getCandidateId().equals(candidateId)) throw new Exception("Resume not found");
        resumeSkillRepository.delete(skill);
    }

    @Override
    public ResumeSkill getResumeSkillEntity(Long skillId, Long resumeId) throws Exception {
        return resumeSkillRepository.findByIdAndResume_Id(skillId, resumeId).orElseThrow(() -> new Exception("Skill not found"));
    }
}
