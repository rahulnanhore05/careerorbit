package io.rahulnanhore.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeSummaryRequest {
    private String targetJobTitle;
    private List<WorkExperienceInfo> workExperiences;
    private List<String> skills;
    private List<EducationInfo> educations;
    private Integer yearsOfExperience;

    @Data
    public static class WorkExperienceInfo{
        private String company;
        private String jobTitle;
        private String description;
    }

    @Data
    public static class EducationInfo{
        private String degree;
        private String field;
        private String institutionName;
    }
}
