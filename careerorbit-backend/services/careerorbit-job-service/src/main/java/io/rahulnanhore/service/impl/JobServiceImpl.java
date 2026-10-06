package io.rahulnanhore.service.impl;

import io.rahulnanhore.client.CompanyClient;
import io.rahulnanhore.domain.JobStatus;
import io.rahulnanhore.dto.request.JobRequest;
import io.rahulnanhore.dto.response.CompanyResponse;
import io.rahulnanhore.dto.response.JobResponse;
import io.rahulnanhore.dto.response.JobTagResponse;
import io.rahulnanhore.mapper.JobMapper;
import io.rahulnanhore.model.Job;
import io.rahulnanhore.model.JobCategory;
import io.rahulnanhore.model.JobSkill;
import io.rahulnanhore.model.JobTag;
import io.rahulnanhore.model.embeddable.JobLocation;
import io.rahulnanhore.model.embeddable.SalaryRange;
import io.rahulnanhore.payload.JobSearchRequest;
import io.rahulnanhore.repository.JobRepository;
import io.rahulnanhore.repository.JobSpecification;
import io.rahulnanhore.service.JobCategoryService;
import io.rahulnanhore.service.JobService;
import io.rahulnanhore.service.JobSkillService;
import io.rahulnanhore.service.JobTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobCategoryService jobCategoryService;
    private final JobSkillService jobSkillService;
    private final JobTagService jobTagService;
    private final CompanyClient companyClient;

    @Override
    public JobResponse createJob(Long employerId, JobRequest request) throws Exception {

        JobCategory jobCategory = jobCategoryService.getCategoryEntityById(request.getCategoryId());
        Set<JobSkill> skills = request.getSkillIds().isEmpty() ? new HashSet<>() : jobSkillService.getSkillsByIds(request.getSkillIds());
        Set<JobTag> tags = request.getTagIds().isEmpty() ? new HashSet<>() : jobTagService.getTagsByIds(request.getTagIds());

        CompanyResponse company = companyClient.getMyCompany(employerId);

        Job job = Job.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .responsibilities(request.getResponsibilities())
                .requirements(request.getRequirements())
                .benefits(request.getBenefits())
                .category(jobCategory)
                .skills(skills)
                .tags(tags)
                .companyId(company.getId())
                .employerId(employerId)
                .jobType(request.getJobType())
                .workMode(request.getWorkMode())
                .experienceLevel(request.getExperienceLevel())
                .location(buildLocation(request))
                .status(request.getStatus() != null ? request.getStatus() : JobStatus.DRAFT)
                .salaryRange(buildSalaryRange(request))
                .openings(request.getOpenings() != null ? request.getOpenings() : 1)
                .applicationDeadline(request.getApplicationDeadline())
                .expiredAt(request.getExpiredAt())
                .build();
        return JobMapper.toJobResponse(jobRepository.save(job), company);
    }

    @Override
    public JobResponse getJobById(Long jobId) throws Exception {

        Job job = jobRepository.findById(jobId).orElseThrow(() -> new Exception("Job not found"));
        return convertToResponse(job);
    }

    @Override
    public List<JobResponse> getJobs(JobSearchRequest jobSearchRequest) {
//        PageRequest pageRequest = PageRequest.of(4, 20);
//        List<Job> jobs = jobRepository.findAll(JobSpecification.build(jobSearchRequest), pageRequest).getContent();
        List<Job> jobs = jobRepository.findAll(JobSpecification.build(jobSearchRequest));
        return jobs.stream().map(this::convertToResponse).toList();
    }

    @Override
    public List<JobResponse> getJobsByCompany(Long companyId) throws Exception {
        List<Job> jobs = jobRepository.findByCompanyId(companyId);
        return jobs.stream()
                .map(this::convertToResponse)
                .toList();
    }


    @Override
    public JobResponse updateJob(Long jobId, Long employerId, JobRequest jobRequest) throws Exception {

        Job job = jobRepository.findById(jobId).orElseThrow(() -> new Exception("Job not found"));
        if (!job.getEmployerId().equals(employerId)) throw new Exception("Unauthorized access");

        if (job.getStatus() == JobStatus.CLOSED) {
            throw new Exception("Job is closed");
        }

        // Title
        if (StringUtils.hasText(jobRequest.getTitle())) {
            job.setTitle(jobRequest.getTitle().trim());
        }

        // Description
        if (StringUtils.hasText(jobRequest.getDescription())) {
            job.setDescription(jobRequest.getDescription().trim());
        }

        // Optional text fields
        if (StringUtils.hasText(jobRequest.getResponsibilities())) {
            job.setResponsibilities(jobRequest.getResponsibilities());
        }

        if (StringUtils.hasText(jobRequest.getRequirements())) {
            job.setRequirements(jobRequest.getRequirements());
        }

        if (StringUtils.hasText(jobRequest.getBenefits())) {
            job.setBenefits(jobRequest.getBenefits());
        }

        // Category
        if (jobRequest.getCategoryId() != null) {
            JobCategory category = jobCategoryService.getCategoryEntityById(jobRequest.getCategoryId());
            job.setCategory(category);
        }

        // Location
        if (StringUtils.hasText(jobRequest.getAddress())) {
            job.getLocation().setAddress(jobRequest.getAddress().trim());
        }

        if (StringUtils.hasText(jobRequest.getCity())) {
            job.getLocation().setCity(jobRequest.getCity().trim());
        }

        if (StringUtils.hasText(jobRequest.getState())) {
            job.getLocation().setState(jobRequest.getState().trim());
        }

        if (StringUtils.hasText(jobRequest.getCountry())) {
            job.getLocation().setCountry(jobRequest.getCountry().trim());
        }

        if (StringUtils.hasText(jobRequest.getPostalCode())) {
            job.getLocation().setPostalCode(jobRequest.getPostalCode().trim());
        }

        // Salary
        if (jobRequest.getMinSalary() != null) {
            if (jobRequest.getMinSalary().signum() < 0) {
                throw new IllegalArgumentException(
                        "Minimum salary cannot be negative"
                );
            }

            job.getSalaryRange().setMinSalary(jobRequest.getMinSalary());
        }

        if (jobRequest.getMaxSalary() != null) {
            if (jobRequest.getMaxSalary().signum() < 0) {
                throw new IllegalArgumentException(
                        "Maximum salary cannot be negative"
                );
            }

            job.getSalaryRange().setMaxSalary(jobRequest.getMaxSalary());
        }

        // Job type
        if (jobRequest.getJobType() != null) {
            job.setJobType(jobRequest.getJobType());
        }

        // Work mode
        if (jobRequest.getWorkMode() != null) {
            job.setWorkMode(jobRequest.getWorkMode());
        }

        // Experience level
        if (jobRequest.getExperienceLevel() != null) {
            job.setExperienceLevel(jobRequest.getExperienceLevel());
        }

        // Status
        if (jobRequest.getStatus() != null) {
            job.setStatus(jobRequest.getStatus());
        }

        // Openings
        if (jobRequest.getOpenings() != null) {

            if (jobRequest.getOpenings() < 1) {
                throw new IllegalArgumentException(
                        "Opening must be greater than or equal to 1"
                );
            }

            job.setOpenings(jobRequest.getOpenings());
        }

        // Application deadline
        if (jobRequest.getApplicationDeadline() != null) {

            if (jobRequest.getApplicationDeadline().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException(
                        "Application deadline cannot be in the past"
                );
            }

            job.setApplicationDeadline(jobRequest.getApplicationDeadline());
        }

        // Expired at
        if (jobRequest.getExpiredAt() != null) {

            if (jobRequest.getExpiredAt().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException(
                        "Expiration date cannot be in the past"
                );
            }

            job.setExpiredAt(jobRequest.getExpiredAt());
        }

        // Skills
        if (jobRequest.getSkillIds() != null) {

            Set<JobSkill> skills = new HashSet<>();

            for (Long skillId : jobRequest.getSkillIds()) {
                JobSkill skill = jobSkillService.getSkillEntityById(skillId);
                skills.add(skill);
            }

            job.setSkills(skills);
        }

        // Tags
        if (jobRequest.getTagIds() != null) {

            HashSet<JobTag> tags = new HashSet<>();

            for (Long tagId : jobRequest.getTagIds()) {
                JobTag jobTag = jobTagService.getJobTagEntityById(tagId);
                tags.add(jobTag);
            }
            job.setTags(tags);
        }

        return convertToResponse(jobRepository.save(job));
    }



    @Override
    public JobResponse publishJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new Exception("Job not found"));
        if (!job.getEmployerId().equals(employerId)) throw new Exception("Unauthorized access");
        if (job.getStatus() == JobStatus.CLOSED) throw new Exception("Job is closed");
        job.setStatus(JobStatus.OPEN);
        job.setPublishedAt(LocalDateTime.now());
        job.setActive(true);
        return convertToResponse(jobRepository.save(job));
    }

    @Override
    public JobResponse closeJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new Exception("Job not found"));
        if (!job.getEmployerId().equals(employerId)) throw new Exception("Unauthorized access");
        job.setStatus(JobStatus.CLOSED);
        job.setClosedAt(LocalDateTime.now());
        job.setActive(false);
        return convertToResponse(jobRepository.save(job));
    }

    @Override
    public void deleteJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new Exception("Job not found"));
        if (!job.getEmployerId().equals(employerId)) throw new Exception("Unauthorized access");
        jobRepository.delete(job);
    }

    @Override
    public List<JobResponse> getAllJobs() {
        return List.of();
    }

    private JobResponse convertToResponse(Job job) {
        CompanyResponse company = companyClient.getCompanyById(job.getCompanyId());
        return JobMapper.toJobResponse(job, company);
    }

    private SalaryRange buildSalaryRange(JobRequest request) {
        return SalaryRange.builder()
                .minSalary(request.getMinSalary())
                .maxSalary(request.getMaxSalary())
                .build();
    }

    private JobLocation buildLocation(JobRequest request) {
        return JobLocation.builder()
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .postalCode(request.getPostalCode())
                .build();
    }
}
