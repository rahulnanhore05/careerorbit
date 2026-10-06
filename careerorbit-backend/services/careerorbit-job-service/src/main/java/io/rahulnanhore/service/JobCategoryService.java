package io.rahulnanhore.service;

import io.rahulnanhore.dto.request.JobCategoryRequest;
import io.rahulnanhore.dto.response.JobCategoryResponse;
import io.rahulnanhore.model.JobCategory;

import java.util.List;

public interface JobCategoryService {

    JobCategoryResponse createJobCategory(JobCategoryRequest jobCategoryRequest) throws Exception;

    List<JobCategoryResponse> getAllCategories();

    JobCategoryResponse getCategoryById(Long id) throws Exception;

    JobCategoryResponse updateCategory(Long id, JobCategoryRequest jobCategoryRequest) throws Exception;

    void deleteCategory(Long id) throws Exception;

    JobCategory getCategoryEntityById(Long id) throws Exception;
}
