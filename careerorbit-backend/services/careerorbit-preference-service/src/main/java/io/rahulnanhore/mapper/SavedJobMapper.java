package io.rahulnanhore.mapper;

import io.rahulnanhore.dto.response.SavedJobResponse;
import io.rahulnanhore.model.SavedJob;

public class SavedJobMapper {
    public static SavedJobResponse toSavedJobResponse(SavedJob savedJob) {
        return SavedJobResponse.builder()
                .id(savedJob.getId())
                .candidateId(savedJob.getCandidateId())
                .jobId(savedJob.getJobId())
                .savedAt(savedJob.getSavedAt())
                .build();
    }
}
