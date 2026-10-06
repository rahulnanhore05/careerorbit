package io.rahulnanhore.repository;

import io.rahulnanhore.model.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {
    List<WorkExperience> findByResume_IdOrderByDisplayOrder(Long resumeId);

    Optional<WorkExperience> findByIdAndResume_Id(Long workExperienceId, Long resumeId);
}
