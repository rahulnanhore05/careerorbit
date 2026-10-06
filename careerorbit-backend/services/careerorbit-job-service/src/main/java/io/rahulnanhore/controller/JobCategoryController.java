package io.rahulnanhore.controller;

import io.rahulnanhore.dto.request.JobCategoryRequest;
import io.rahulnanhore.dto.response.JobCategoryResponse;
import io.rahulnanhore.service.JobCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs/categories")
@RequiredArgsConstructor
public class JobCategoryController {
    private final JobCategoryService jobCategoryService;

    @PostMapping
    ResponseEntity<JobCategoryResponse> create(@Valid @RequestBody JobCategoryRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobCategoryService.createJobCategory(request));
    }

    @GetMapping
    ResponseEntity<List<JobCategoryResponse>> getCategories() {
        return ResponseEntity.ok(jobCategoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    ResponseEntity<JobCategoryResponse> getCategoryById(
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobCategoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<JobCategoryResponse> updateCategory(
            @PathVariable Long id, @RequestBody JobCategoryRequest request) throws Exception {
        return ResponseEntity.ok(jobCategoryService.updateCategory(id, request));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCategory(@PathVariable Long id) throws Exception {
        jobCategoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
