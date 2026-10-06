package io.rahulnanhore.repository;

import io.rahulnanhore.model.ResumeSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResumeSkillRepository extends JpaRepository<ResumeSkill, Long> {

    List<ResumeSkill> findByResume_IdOrderByDisplayOrderAsc(Long resumeId);

    Optional<ResumeSkill> findByIdAndResume_Id(Long skillId, Long resumeId);
}
