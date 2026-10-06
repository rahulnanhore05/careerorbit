package io.rahulnanhore.repository;

import io.rahulnanhore.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {

    // For standard List results with Specification
    @Override
    @EntityGraph(attributePaths = {"category"})
    List<Job> findAll(Specification<Job> spec);

    // If you also use pagination with Specification
    @Override
    @EntityGraph(attributePaths = {"category"})
    Page<Job> findAll(Specification<Job> spec, Pageable pageable);

    List<Job> findByCompanyId(Long companyId);
}
