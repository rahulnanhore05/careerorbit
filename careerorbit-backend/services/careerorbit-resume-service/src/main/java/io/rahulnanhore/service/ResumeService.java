package io.rahulnanhore.service;

import io.rahulnanhore.dto.PersonalInfoDto;
import io.rahulnanhore.dto.request.ResumeRequest;
import io.rahulnanhore.dto.response.ResumeResponse;
import io.rahulnanhore.model.Resume;

import java.util.List;

public interface ResumeService {

    ResumeResponse createResume(Long candidateId , ResumeRequest resumeRequest);
    ResumeResponse getResumeById(Long candidateId , Long resumeId) throws Exception;
    List<ResumeResponse> getAllMyResumes(Long candidateId);
    ResumeResponse updatePersonalInfoResume( Long resumeId, Long candidateId , PersonalInfoDto personalInfoDto) throws Exception;
    ResumeResponse updateSummary(Long resumeId, Long candidateId, String summary) throws Exception;
    ResumeResponse setDefaultResume(Long resumeId, Long candidateId) throws Exception;
    void deleteResume(Long candidateId , Long resumeId) throws Exception;
    Resume getResumeEntity(Long resumeId) throws Exception;
}
