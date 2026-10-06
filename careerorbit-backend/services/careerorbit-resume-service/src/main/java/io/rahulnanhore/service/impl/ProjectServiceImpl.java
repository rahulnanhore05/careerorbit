package io.rahulnanhore.service.impl;

import io.rahulnanhore.dto.request.ProjectRequest;
import io.rahulnanhore.dto.response.ProjectResponse;
import io.rahulnanhore.mapper.ProjectMapper;
import io.rahulnanhore.model.Project;
import io.rahulnanhore.model.Resume;
import io.rahulnanhore.repository.ProjectRepository;
import io.rahulnanhore.service.ProjectService;
import io.rahulnanhore.service.ResumeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ResumeService resumeService;

    @Override
    public ProjectResponse createProject(Long resumeId, Long candidateId, ProjectRequest projectRequest) throws Exception {
        Resume resume = resumeService.getResumeEntity(resumeId);
        if (!resume.getCandidateId().equals(candidateId)) throw new Exception("Resume not found");

        Project project = Project.builder()
                .resume(resume)
                .title(projectRequest.getTitle())
                .description(projectRequest.getDescription())
                .technologies(projectRequest.getTechnologies() != null ? projectRequest.getTechnologies() : new ArrayList<>())
                .projectUrl(projectRequest.getProjectUrl())
                .sourceCodeUrl(projectRequest.getSourceCodeUrl())
                .startDate(projectRequest.getStartDate())
                .endDate(projectRequest.getEndDate())
                .isCurrentlyWorking(Boolean.TRUE.equals(projectRequest.getIsCurrentlyWorking()))
                .displayOrder(projectRequest.getDisplayOrder() != null ? projectRequest.getDisplayOrder() : 0)
                .build();
        return ProjectMapper.toProjectResponse(projectRepository.save(project));
    }

    @Override
    public List<ProjectResponse> getProjects(Long resumeId) {
        List<Project> projects = projectRepository.findByResume_IdOrderByDisplayOrderAsc(resumeId);
        return projects.stream().map(ProjectMapper::toProjectResponse).toList();
    }

    @Override
    public ProjectResponse updateProject(Long resumeId, Long candidateId, Long projectId, ProjectRequest projectRequest) throws Exception {
        Project project = projectRepository.findByIdAndResume_Id(projectId, resumeId).orElseThrow(() -> new Exception("Project not found"));
        if (!project.getResume().getCandidateId().equals(candidateId)) throw new Exception("Resume not found");

        if (StringUtils.hasText(projectRequest.getTitle())) project.setTitle(projectRequest.getTitle());
        if (StringUtils.hasText(projectRequest.getDescription())) project.setDescription(projectRequest.getDescription());
        if (projectRequest.getTechnologies() != null) project.setTechnologies(projectRequest.getTechnologies());
        if (StringUtils.hasText(projectRequest.getProjectUrl())) project.setProjectUrl(projectRequest.getProjectUrl());
        if (StringUtils.hasText(projectRequest.getSourceCodeUrl())) project.setSourceCodeUrl(projectRequest.getSourceCodeUrl());
        if (projectRequest.getStartDate() != null) project.setStartDate(projectRequest.getStartDate());
        if (projectRequest.getEndDate() != null) project.setEndDate(projectRequest.getEndDate());
        if (projectRequest.getIsCurrentlyWorking() != null) project.setIsCurrentlyWorking(projectRequest.getIsCurrentlyWorking());
        if (projectRequest.getDisplayOrder() != null) project.setDisplayOrder(projectRequest.getDisplayOrder());

        return ProjectMapper.toProjectResponse(projectRepository.save(project));
    }

    @Override
    public void deleteProject(Long resumeId, Long candidateId, Long projectId) throws Exception {
        Project project = projectRepository.findByIdAndResume_Id(projectId, resumeId).orElseThrow(() -> new Exception("Project not found"));
        if (!project.getResume().getCandidateId().equals(candidateId)) throw new Exception("Resume not found");
        projectRepository.delete(project);
    }

    @Override
    public Project getProjectEntity(Long resumeId, Long projectId) throws Exception {
        return projectRepository.findByIdAndResume_Id(projectId, resumeId).orElseThrow(() -> new Exception("Project not found"));
    }
}
