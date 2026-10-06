package io.rahulnanhore.repository;

import io.rahulnanhore.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByResume_IdOrderByDisplayOrderAsc(Long resumeId);
    Optional<Project> findByIdAndResume_Id(Long id, Long resumeId);

}
