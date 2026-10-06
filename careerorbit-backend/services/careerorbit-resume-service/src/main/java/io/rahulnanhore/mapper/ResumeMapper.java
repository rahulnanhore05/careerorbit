package io.rahulnanhore.mapper;

import io.rahulnanhore.dto.PersonalInfoDto;
import io.rahulnanhore.dto.response.*;
import io.rahulnanhore.model.Resume;
import io.rahulnanhore.model.embeddable.PersonalInfo;

import java.util.ArrayList;
import java.util.List;

public class ResumeMapper {
    public static ResumeResponse toResumeResponse(Resume resume){

        List<EducationResponse> educations = resume.getEducations() == null ? new ArrayList<EducationResponse>() : resume.getEducations().stream().map(EducationMapper::toEducationResponse).toList();
        List<WorkExperienceResponse> experiences = resume.getExperiences() == null ? new ArrayList<WorkExperienceResponse>() : resume.getExperiences().stream().map(WorkExperienceMapper::toWorkExperienceResponse).toList();
        List<ProjectResponse> projects = resume.getProjects() == null ? new ArrayList<ProjectResponse>() : resume.getProjects().stream().map(ProjectMapper::toProjectResponse).toList();
        List<ResumeSkillResponse> skills = resume.getSkills() == null ? new ArrayList<ResumeSkillResponse>() : resume.getSkills().stream().map(ResumeSkillMapper::toResumeSkillResponse).toList();
        List<LanguageResponse> languages = resume.getLanguages() == null ? new ArrayList<LanguageResponse>() : resume.getLanguages().stream().map(LanguageMapper::toLanguageResponse).toList();
        List<CertificateResponse> certificates = resume.getCertificates() == null ? new ArrayList<CertificateResponse>() : resume.getCertificates().stream().map(CertificateMapper::toCertificateResponse).toList();

        return ResumeResponse.builder()
                .id(resume.getId())
                .candidateId(resume.getCandidateId())
                .title(resume.getTitle())
                .resumeTemplate(resume.getResumeTemplate())
                .visibility(resume.getVisibility())
                .isDefault(resume.getIsDefault())
                .summary(resume.getSummary())
                .personalInfoDto(toPersonInfoDto(resume.getPersonalInfo()))
                .completionScore(resume.getCompletionScore())

                .educations(educations)
                .experiences(experiences)
                .projects(projects)
                .skills(skills)
                .languages(languages)
                .certificates(certificates)

                .createdAt(resume.getCreatedAt())
                .updatedAt(resume.getUpdatedAt())
                .build();
    }

    public static PersonalInfoDto toPersonInfoDto(PersonalInfo personalInfo){

        if(personalInfo == null) return new PersonalInfoDto();

        return PersonalInfoDto.builder()
                .firstName(personalInfo.getFirstName())
                .lastName(personalInfo.getLastName())
                .headline(personalInfo.getHeadline())
                .email(personalInfo.getEmail())
                .phone(personalInfo.getPhone())
                .city(personalInfo.getCity())
                .country(personalInfo.getCountry())
                .linkedin(personalInfo.getLinkedInUrl())
                .github(personalInfo.getGithubUrl())
                .portfolio(personalInfo.getPortfolioUrl())
                .website(personalInfo.getWebsiteUrl())
                .build();
    }

}
