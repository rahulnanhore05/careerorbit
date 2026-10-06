package io.rahulnanhore.dto.response;

import io.rahulnanhore.domain.ResumeTemplate;
import io.rahulnanhore.domain.ResumeVisibility;
import io.rahulnanhore.dto.PersonalInfoDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeResponse {
    private Long id;
    private Long candidateId;
    private String title;
    private ResumeTemplate resumeTemplate;
    private ResumeVisibility visibility;
    private Boolean isDefault;
    private PersonalInfoDto personalInfoDto;
    private String summary;
    private Integer completionScore;

    private List<EducationResponse> educations;
    private List<WorkExperienceResponse> experiences;
    private List<ProjectResponse> projects;
    private List<ResumeSkillResponse> skills;
    private List<LanguageResponse> languages;
    private List<CertificateResponse> certificates;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
