package io.rahulnanhore.service;

import io.rahulnanhore.dto.request.ResumeSkillRequest;
import io.rahulnanhore.dto.response.ResumeSkillResponse;
import io.rahulnanhore.model.ResumeSkill;

import java.util.List;

public interface ResumeSkillService {
    ResumeSkillResponse createResumeSkill(Long resumeId, Long candidateId, ResumeSkillRequest request) throws Exception;
    List<ResumeSkillResponse> getSKills(Long resumeId);
    ResumeSkillResponse updateResumeSkill(Long skillId, Long resumeId, Long candidateId, ResumeSkillRequest request) throws Exception;
    void deleteResumeSkill(Long skillId, Long resumeId, Long candidateId) throws Exception;
    ResumeSkill getResumeSkillEntity(Long skillId, Long resumeId) throws Exception;
}
