package io.rahulnanhore.mapper;

import io.rahulnanhore.dto.response.*;
import io.rahulnanhore.model.Application;
import io.rahulnanhore.model.ApplicationNote;

import java.util.List;

public class ApplicationMapper {
    public static ApplicationResponse toApplicationResponse(
            Application application,
            List<ApplicationNoteResponse> notes,
            UserResponse candidate,
            JobResponse job,
            CompanyResponse company
    ) {
        return ApplicationResponse.builder()
                .id(application.getId())
                .candidate(candidate)
                .job(job)
                .company(company)
                .status(application.getStatus())
//                .employerId(job.getEmployerId())
                .resumeId(application.getResumeId())
                .coverLetter(application.getCoverLetter())
                .expectedSalary(application.getExpectedSalary())
                .availableFrom(application.getAvailableFrom())
                .isStarred(application.getIsStarred())
                .notes(notes)
                .withdrawnAt(application.getWithdrawnAt())
                .withdrawalReason(application.getWithdrawalReason())
                .createdAt(application.getCreatedAt())
                .updatedAt(application.getUpdatedAt())
                .build();
    }

    public static ApplicationNoteResponse toApplicationNoteResponse(ApplicationNote note) {
        return ApplicationNoteResponse.builder()
                .id(note.getId())
                .addedByUserId(note.getAddedByUserId())
                .content(note.getContent())
                .createdAt(note.getCreatedAt())
                .build();

    }
}
