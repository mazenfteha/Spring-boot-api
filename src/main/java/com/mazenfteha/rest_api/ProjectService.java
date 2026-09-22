package com.mazenfteha.rest_api;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mazenfteha.rest_api.exception.ResourceNotFoundException;

@Service 
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final SoftwareEngineerRepository softwareEngineerRepository;

    public ProjectService(ProjectRepository projectRepository, SoftwareEngineerRepository softwareEngineerRepository) {
        this.projectRepository = projectRepository;
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    public Project createProject(String name, String description, Integer softwareEngineerId) {
        SoftwareEngineer softwareEngineer = softwareEngineerRepository.findById(softwareEngineerId)
                .orElseThrow(() -> new ResourceNotFoundException("Software engineer not found"));

        Project project = new Project(null, name, description, softwareEngineer);
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Integer id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    }

}
