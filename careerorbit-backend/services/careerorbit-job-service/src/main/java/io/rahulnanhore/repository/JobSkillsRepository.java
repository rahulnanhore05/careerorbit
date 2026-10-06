package io.rahulnanhore.repository;

import io.rahulnanhore.domain.SkillCategory;
import io.rahulnanhore.model.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobSkillsRepository extends JpaRepository<JobSkill, Long> {
    boolean existsByName(String name);
    boolean existsBySlug(String slug);
    List<JobSkill> findAllByCategory(SkillCategory category);
}
