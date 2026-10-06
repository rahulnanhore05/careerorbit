package io.rahulnanhore.service.impl;

import io.rahulnanhore.dto.request.SavedJobRequest;
import io.rahulnanhore.dto.response.SavedJobResponse;
import io.rahulnanhore.mapper.SavedJobMapper;
import io.rahulnanhore.model.SavedJob;
import io.rahulnanhore.repository.SavedJobRepository;
import io.rahulnanhore.service.SavedJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SavedJobServiceImpl implements SavedJobService {

    private final SavedJobRepository savedJobRepository;

    @Override
    public SavedJobResponse saveJob(Long candidateId, SavedJobRequest savedJobRequest) throws Exception {
        if (savedJobRepository.existsByCandidateIdAndJobId(candidateId, savedJobRequest.getJobId())) throw new Exception("Job already saved");

        SavedJob savedJob = SavedJob.builder()
                .candidateId(candidateId)
                .jobId(savedJobRequest.getJobId())
                .build();
        return SavedJobMapper.toSavedJobResponse(savedJobRepository.save(savedJob));
    }

    @Override
    public void unsaveJob(Long candidateId, Long savedJobId) throws Exception {
        SavedJob savedJob = savedJobRepository.findById(savedJobId).orElseThrow(() -> new Exception("Saved job not found"));
        if (!savedJob.getCandidateId().equals(candidateId)) throw new Exception("Saved job not found");
        savedJobRepository.delete(savedJob);
    }

    @Override
    public List<SavedJobResponse> getSavedJobs(Long candidateId) {
        return savedJobRepository.findByCandidateId(candidateId).stream()
                .map(SavedJobMapper::toSavedJobResponse)
                .toList();
    }

    @Override
    public boolean isJobSaved(Long candidateId, Long jobId) {
        return savedJobRepository.existsByCandidateIdAndJobId(candidateId, jobId);
    }
}
