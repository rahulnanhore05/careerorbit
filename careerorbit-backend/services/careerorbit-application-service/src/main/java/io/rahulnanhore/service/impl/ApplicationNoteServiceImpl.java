package io.rahulnanhore.service.impl;

import io.rahulnanhore.dto.request.ApplicationNoteRequest;
import io.rahulnanhore.dto.response.ApplicationNoteResponse;
import io.rahulnanhore.mapper.ApplicationMapper;
import io.rahulnanhore.model.Application;
import io.rahulnanhore.model.ApplicationNote;
import io.rahulnanhore.repository.ApplicationNoteRepository;
import io.rahulnanhore.service.ApplicationNoteService;
import io.rahulnanhore.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationNoteServiceImpl implements ApplicationNoteService {

    private final ApplicationNoteRepository applicationNoteRepository;
    private final ApplicationService applicationService;

    @Override
    public ApplicationNoteResponse createNote(Long applicationId, Long employerId, ApplicationNoteRequest request) throws Exception {
        Application application = applicationService.getApplicationEntity(applicationId);
        if (!application.getEmployerId().equals(employerId)) throw new Exception("Application not found");
        ApplicationNote note = ApplicationNote.builder()
                .application(application)
                .addedByUserId(employerId)
                .content(request.getContent())
                .build();
        return ApplicationMapper.toApplicationNoteResponse(applicationNoteRepository.save(note));
    }

    @Override
    public List<ApplicationNoteResponse> getNotesByApplication(Long applicationId) {
        return applicationNoteRepository.findAllByApplication_Id(applicationId)
                .stream()
                .map(ApplicationMapper::toApplicationNoteResponse)
                .toList();
    }

    @Override
    public void deleteNote(Long applicationId, Long noteId, Long employerId) throws Exception {
        ApplicationNote note = applicationNoteRepository.findByIdAndApplication_Id(noteId, applicationId).orElseThrow(() -> new Exception("Note not found"));
        if (!note.getAddedByUserId().equals(employerId)) throw new Exception("Note not found");
        applicationNoteRepository.delete(note);
    }
}
