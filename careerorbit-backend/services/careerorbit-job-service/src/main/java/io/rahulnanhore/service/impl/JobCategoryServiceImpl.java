package io.rahulnanhore.service.impl;

import io.rahulnanhore.dto.request.JobCategoryRequest;
import io.rahulnanhore.dto.response.JobCategoryResponse;
import io.rahulnanhore.mapper.JobCategoryMapper;
import io.rahulnanhore.model.JobCategory;
import io.rahulnanhore.repository.JobCategoryRepository;
import io.rahulnanhore.service.JobCategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class JobCategoryServiceImpl implements JobCategoryService {

    private final JobCategoryRepository jobCategoryRepository;

    @Override
    public JobCategoryResponse createJobCategory(JobCategoryRequest request) throws Exception {

        if (jobCategoryRepository.existsByName(request.getName())) {
            throw new Exception("Category already exists");
        }

        JobCategory jobCategory = JobCategory.builder()
                .name(request.getName())
                .description(request.getDescription())
                .slug(generateSlug(request.getName()))
                .iconUrl(request.getIconUrl())
                .parent(request.getParentId() != null ? getCategoryEntityById(request.getParentId()) : null)
                .build();

        return JobCategoryMapper.toJobCategoryResponse(jobCategoryRepository.save(jobCategory), false);
    }

    @Override
    public List<JobCategoryResponse> getAllCategories() {

        List<JobCategory> jobCategories = jobCategoryRepository.findAll();
        return jobCategories.stream()
                .map(jobCategory -> JobCategoryMapper.toJobCategoryResponse(jobCategory, true))
                .toList();
    }

    @Override
    public JobCategoryResponse getCategoryById(Long id) throws Exception {
        JobCategory jobCategory = jobCategoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));
        return JobCategoryMapper.toJobCategoryResponse(jobCategory, true);
    }

    @Override
    public JobCategoryResponse updateCategory(Long id, JobCategoryRequest request) throws Exception {

        JobCategory jobCategory = jobCategoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));

        if (StringUtils.hasText(request.getName()) && jobCategoryRepository.existsByName(request.getName())) throw new Exception("Category already exists");
        if (StringUtils.hasText(request.getName())) jobCategory.setName(request.getName());

        if (request.getParentId() != null) {
            if (request.getParentId().equals(jobCategory.getParent().getId())) throw new Exception("Category cannot be its own parent");
            jobCategory.setParent(getCategoryEntityById(request.getParentId()));
        }
        if (StringUtils.hasText(request.getDescription())) jobCategory.setDescription(request.getDescription());
        if (StringUtils.hasText(request.getIconUrl())) jobCategory.setIconUrl(request.getIconUrl());

        jobCategory.setSlug(generateSlug(request.getName()));

        jobCategoryRepository.save(jobCategory);
        return JobCategoryMapper.toJobCategoryResponse(jobCategory, true);
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        JobCategory jobCategory = jobCategoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));
        jobCategoryRepository.deleteById(jobCategory.getId());
    }

    @Override
    public JobCategory getCategoryEntityById(Long id) throws Exception {
        return jobCategoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));
    }

    private String generateSlug(String name) {
        String slug = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("[\\s-]+", "-");
        if (!jobCategoryRepository.existsBySlug(slug)) {
            return slug;
        }
        int counter = 1;
        while (jobCategoryRepository.existsBySlug(slug + "-" + counter)) {
            counter++;
        }
        return slug + "-" + counter;
    }
}
