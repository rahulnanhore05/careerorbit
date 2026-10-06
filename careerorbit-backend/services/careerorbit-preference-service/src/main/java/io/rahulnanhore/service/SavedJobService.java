package io.rahulnanhore.service;

import io.rahulnanhore.dto.request.SavedJobRequest;
import io.rahulnanhore.dto.response.SavedJobResponse;

import java.util.List;

public interface SavedJobService {
    SavedJobResponse saveJob(Long candidateId, SavedJobRequest savedJobRequest) throws Exception;
    void unsaveJob(Long candidateId, Long savedJobId) throws Exception;
    List<SavedJobResponse> getSavedJobs(Long candidateId);
    boolean isJobSaved(Long candidateId, Long jobId);
}
