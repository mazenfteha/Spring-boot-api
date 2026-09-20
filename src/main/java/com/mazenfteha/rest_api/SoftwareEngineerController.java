package com.mazenfteha.rest_api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService softwareEngineerService;

    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    @GetMapping()
    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        return softwareEngineerService.getAllSoftwareEngineers();
    }

    @PostMapping()
    public SoftwareEngineer createSoftwareEngineer(@RequestBody SoftwareEngineer softwareEngineer) {
        return softwareEngineerService.createSoftwareEngineer(softwareEngineer.getId(), softwareEngineer.getName(),
                softwareEngineer.getTechStack());
    }

    @GetMapping("/{id}")
    public SoftwareEngineer getSoftwareEngineerById(@PathVariable Integer id) {
        return softwareEngineerService.getSoftwareEngineerById(id);
    }

    @PutMapping("/{id}")
    public SoftwareEngineer updateSoftwareEngineer(@PathVariable Integer id,
            @RequestBody SoftwareEngineer softwareEngineer) {
        return softwareEngineerService.updateSoftwareEngineer(id, softwareEngineer.getName(),
                softwareEngineer.getTechStack());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoftwareEngineer(@PathVariable Integer id) {
        softwareEngineerService.deleteSoftwareEngineer(id);
        return ResponseEntity.noContent().build();
    }

}
