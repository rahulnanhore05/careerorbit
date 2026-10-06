package io.rahulnanhore.repository;

import io.rahulnanhore.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findByResume_IdOrderByDisplayOrderAsc(Long resumeId);
    Optional<Education> findByIdAndResume_Id(Long id, Long resumeId);
}
