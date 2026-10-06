package io.rahulnanhore.repository;

import io.rahulnanhore.model.ApplicationNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationNoteRepository extends JpaRepository<ApplicationNote, Long> {
    List<ApplicationNote> findAllByApplication_Id(Long applicationId);
    Optional<ApplicationNote> findByIdAndApplication_Id(Long noteId, Long applicationId);
}
