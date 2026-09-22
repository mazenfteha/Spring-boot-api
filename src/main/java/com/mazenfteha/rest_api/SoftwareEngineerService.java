package com.mazenfteha.rest_api;

import java.util.List;

import org.springframework.stereotype.Service;


import com.mazenfteha.rest_api.exception.ResourceNotFoundException;

@Service
public class SoftwareEngineerService {

    private final SoftwareEngineerRepository softwareEngineerRepository;

    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository) {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        return softwareEngineerRepository.findAll();
    }

    public SoftwareEngineer createSoftwareEngineer(Integer id, String name, String techStack) {
        SoftwareEngineer softwareEngineer = new SoftwareEngineer(id, name, techStack);
        return softwareEngineerRepository.save(softwareEngineer);
    }

    public SoftwareEngineer getSoftwareEngineerById(Integer id) {
        return softwareEngineerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Software engineer not found"));
    }

    public SoftwareEngineer updateSoftwareEngineer(Integer id, String name, String techStack) {

        SoftwareEngineer engineer = softwareEngineerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Software engineer not found"));

        engineer.setName(name);
        engineer.setTechStack(techStack);

        return softwareEngineerRepository.save(engineer);
    }

    public void deleteSoftwareEngineer(Integer id) {
        if (!softwareEngineerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Software engineer not found");
        }

        softwareEngineerRepository.deleteById(id);
    }

}
