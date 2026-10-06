package io.rahulnanhore.service;

import io.rahulnanhore.dto.request.EducationRequest;
import io.rahulnanhore.dto.response.EducationResponse;
import io.rahulnanhore.model.Education;

import java.util.List;

public interface EducationService {
    EducationResponse createEducation(Long resumeId, Long candidateId, EducationRequest request) throws Exception;
    List<EducationResponse> getEducations(Long resumeId);
    EducationResponse updateEducation(Long educationId, Long resumeId, Long candidateId, EducationRequest request) throws Exception;
    void deleteEducation(Long educationId, Long resumeId, Long candidateId) throws Exception;
    Education getEducationEntity(Long educationId) throws Exception;
}
