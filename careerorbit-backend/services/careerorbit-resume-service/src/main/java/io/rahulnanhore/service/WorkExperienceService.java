package io.rahulnanhore.service;

import io.rahulnanhore.dto.request.WorkExperienceRequest;
import io.rahulnanhore.dto.response.WorkExperienceResponse;
import io.rahulnanhore.model.WorkExperience;

import java.util.List;

public interface WorkExperienceService {

    WorkExperienceResponse createWorkExperience(Long resumeId, Long candidateId, WorkExperienceRequest request) throws Exception;
    List<WorkExperienceResponse> getWorkExperiences(Long resumeId);
    WorkExperienceResponse updateWorkExperience(Long resumeId, Long workExperienceId, Long candidateId, WorkExperienceRequest request) throws Exception;
    void deleteWorkExperience(Long resumeId, Long candidateId, Long workExperienceId) throws Exception;
    WorkExperience getWorkExperienceEntity(Long workExperienceId) throws Exception;

}
