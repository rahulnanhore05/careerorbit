package io.rahulnanhore.repository;

import io.rahulnanhore.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    List<Resume> findByCandidateId(Long candidateId);
    Optional<Resume> findByCandidateIdAndIsDefaultTrue(Long candidateId);
    Optional<Resume> findByIdAndCandidateId(Long resumeId, Long candidateId);
}
