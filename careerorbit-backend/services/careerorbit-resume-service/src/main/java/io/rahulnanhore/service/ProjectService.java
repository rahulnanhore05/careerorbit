package io.rahulnanhore.service;

import io.rahulnanhore.dto.request.ProjectRequest;
import io.rahulnanhore.dto.response.ProjectResponse;
import io.rahulnanhore.model.Project;

import java.util.List;

public interface ProjectService {
    ProjectResponse createProject(Long resumeId, Long candidateId, ProjectRequest projectRequest) throws Exception;
    List<ProjectResponse> getProjects(Long resumeId);
    ProjectResponse updateProject(Long resumeId, Long candidateId, Long projectId, ProjectRequest projectRequest) throws Exception;
    void deleteProject(Long resumeId, Long candidateId, Long projectId) throws Exception;
    Project getProjectEntity(Long resumeId, Long projectId) throws Exception;
}
