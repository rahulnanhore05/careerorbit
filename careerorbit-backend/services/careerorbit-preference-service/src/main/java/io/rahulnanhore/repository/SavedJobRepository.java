package io.rahulnanhore.repository;

import io.rahulnanhore.model.SavedJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SavedJobRepository extends JpaRepository<SavedJob, Long> {
    Optional<SavedJob> findByCandidateIdAndJobId(Long candidateId, Long jobId);
    List<SavedJob> findByCandidateId(Long candidateId);
    boolean existsByCandidateIdAndJobId(Long candidateId, Long jobId);
}
