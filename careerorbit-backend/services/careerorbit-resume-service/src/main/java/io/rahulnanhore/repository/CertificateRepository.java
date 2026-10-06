package io.rahulnanhore.repository;

import io.rahulnanhore.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByResume_IdOrderByDisplayOrderAsc(Long resumeId);
    Optional<Certificate> findByIdAndResume_Id(Long id, Long resumeId);
}
