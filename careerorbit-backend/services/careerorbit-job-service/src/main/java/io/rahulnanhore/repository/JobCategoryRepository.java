package io.rahulnanhore.repository;

import io.rahulnanhore.model.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobCategoryRepository extends JpaRepository<JobCategory, Long> {
    boolean existsByName(String name);
    boolean existsBySlug(String slug);
}
