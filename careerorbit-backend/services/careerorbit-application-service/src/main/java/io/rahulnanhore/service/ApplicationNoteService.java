package io.rahulnanhore.service;

import io.rahulnanhore.dto.request.ApplicationNoteRequest;
import io.rahulnanhore.dto.response.ApplicationNoteResponse;

import java.util.List;

public interface ApplicationNoteService {
    ApplicationNoteResponse createNote(Long applicationId, Long employerId, ApplicationNoteRequest request) throws Exception;
    List<ApplicationNoteResponse> getNotesByApplication(Long applicationId);
    void deleteNote(Long applicationId, Long noteId, Long employerId) throws Exception;
}