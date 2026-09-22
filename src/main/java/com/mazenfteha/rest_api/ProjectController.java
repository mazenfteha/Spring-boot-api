package com.mazenfteha.rest_api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mazenfteha.rest_api.dto.CreateProjectRequest;
import com.mazenfteha.rest_api.dto.ProjectResponse;
import com.mazenfteha.rest_api.dto.UpdateProjectRequest;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @ResponseStatus(HttpStatus.CREATED)
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

    @PutMapping("/{id}")
    public ProjectResponse updateProject(@PathVariable Integer id, @Valid @RequestBody UpdateProjectRequest request) {
        Project project = projectService.updateProject(id, request.name(), request.description(), request.softwareEngineerId());
        return toResponse(project);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    private ProjectResponse toResponse(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getSoftwareEngineer().getId());
    }

}
