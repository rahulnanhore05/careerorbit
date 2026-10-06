package io.rahulnanhore.repository;

import io.rahulnanhore.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    List<Language> findByResume_IdOrderByDisplayOrderAsc(Long resumeId);
    Language findByIdAndResume_Id(Long id, Long resumeId);
}
