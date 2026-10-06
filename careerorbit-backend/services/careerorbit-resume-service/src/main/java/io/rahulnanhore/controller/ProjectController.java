package io.rahulnanhore.controller;

import io.rahulnanhore.dto.request.ProjectRequest;
import io.rahulnanhore.dto.response.ProjectResponse;
import io.rahulnanhore.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resumes/{resumeId}/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @Valid @RequestBody ProjectRequest projectRequest) throws Exception {

        ProjectResponse response =
                projectService.createProject(resumeId, candidateId, projectRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getProjects(
            @PathVariable Long resumeId) {

        List<ProjectResponse> projects = projectService.getProjects(resumeId);
        return ResponseEntity.ok(projects);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long projectId,
            @RequestBody ProjectRequest projectRequest) throws Exception {

        ProjectResponse response = projectService.updateProject(
                resumeId,
                candidateId,
                projectId,
                projectRequest
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long projectId) throws Exception {

        projectService.deleteProject(resumeId, candidateId, projectId);
        return ResponseEntity.noContent().build();
    }
}