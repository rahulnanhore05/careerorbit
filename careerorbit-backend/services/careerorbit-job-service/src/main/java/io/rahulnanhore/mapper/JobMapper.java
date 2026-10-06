package io.rahulnanhore.mapper;

import io.rahulnanhore.dto.response.CompanyResponse;
import io.rahulnanhore.dto.response.JobResponse;
import io.rahulnanhore.model.Job;
import io.rahulnanhore.model.embeddable.JobLocation;
import io.rahulnanhore.model.embeddable.SalaryRange;

import java.util.stream.Collectors;

public class JobMapper {

    public static JobResponse toJobResponse(Job job, CompanyResponse company){

        JobLocation location = job.getLocation();
        SalaryRange salaryRange = job.getSalaryRange();


        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .responsibilities(job.getResponsibilities())
                .requirements(job.getRequirements())
                .benefits(job.getBenefits())

                .company(company)
                .employerId(job.getEmployerId())
                .address(location.getAddress())
                .city(location.getCity())
                .state(location.getState())
                .country(location.getCountry())
                .postalCode(location.getPostalCode())

                .category(JobCategoryMapper.toJobCategoryResponse(job.getCategory(), false))
                .skills(job.getSkills().stream().map(JobSkillMapper::toJobSkillResponse).collect(Collectors.toSet()))
                .tags(job.getTags().stream().map(JobTagMapper::toJobTagResponse).collect(Collectors.toSet()))

                .minSalary(salaryRange.getMinSalary())
                .maxSalary(salaryRange.getMaxSalary())

                .jobType(job.getJobType())
                .workMode(job.getWorkMode())
                .experienceLevel(job.getExperienceLevel())
                .status(job.getStatus())

                .openings(job.getOpenings())
                .applicationDeadline(job.getApplicationDeadline())
                .expiredAt(job.getExpiredAt())
                .active(job.getActive())

                .publishedAt(job.getPublishedAt())
                .closedAt(job.getClosedAt())
                .createdAt(job.getCreatedAt())
                .updatedAt(job.getUpdatedAt())
                .build();
    }
}
