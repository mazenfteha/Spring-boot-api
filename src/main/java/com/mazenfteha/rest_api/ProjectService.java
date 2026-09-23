package com.mazenfteha.rest_api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Project updateProject(Integer id, String name, String description, Integer softwareEngineerId) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        SoftwareEngineer softwareEngineer = softwareEngineerRepository.findById(softwareEngineerId)
                .orElseThrow(() -> new ResourceNotFoundException("Software engineer not found"));

        project.setName(name);
        project.setDescription(description);
        project.setSoftwareEngineer(softwareEngineer);

        return projectRepository.save(project);
    }

    public void deleteProject( Integer id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found");
        }

        projectRepository.deleteById(id);
    }

    public Page<Project> searchProjects(Integer softwareEngineerId, Pageable pageable) {
        if (softwareEngineerId != null) {
            return projectRepository.findBySoftwareEngineerId(softwareEngineerId, pageable);
        } else {
            return projectRepository.findAll(pageable);
        }
    }
    

}
