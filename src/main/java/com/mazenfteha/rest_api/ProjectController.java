package com.mazenfteha.rest_api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mazenfteha.rest_api.dto.CreateProjectRequest;
import com.mazenfteha.rest_api.dto.ProjectResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ProjectResponse createProject(@Valid @RequestBody CreateProjectRequest request) {
        Project project = projectService.createProject(request.name(), request.description(),
                request.softwareEngineerId());
        return toResponse(project);
    }

    @GetMapping()
    public List<ProjectResponse> getAllProjects() {
        return projectService.getAllProjects().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProjectResponse getProjectById(@PathVariable Integer id) {
        return toResponse(projectService.getProjectById(id));
    }
    
    

    private ProjectResponse toResponse(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getSoftwareEngineer().getId());
    }

}
