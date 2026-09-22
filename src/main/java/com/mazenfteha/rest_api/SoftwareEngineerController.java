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

import jakarta.validation.Valid;

import com.mazenfteha.rest_api.dto.CreateSoftwareEngineerRequest;
import com.mazenfteha.rest_api.dto.UpdateSoftwareEngineerRequest;

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
    public SoftwareEngineer createSoftwareEngineer(@Valid @RequestBody CreateSoftwareEngineerRequest request) {
        return softwareEngineerService.createSoftwareEngineer(null, request.name(), request.techStack());
    }

    @GetMapping("/{id}")
    public SoftwareEngineer getSoftwareEngineerById(@PathVariable Integer id) {
        return softwareEngineerService.getSoftwareEngineerById(id);
    }

    @PutMapping("/{id}")
    public SoftwareEngineer updateSoftwareEngineer(@PathVariable Integer id,
            @Valid @RequestBody UpdateSoftwareEngineerRequest request) {
        return softwareEngineerService.updateSoftwareEngineer(id, request.name(),
                request.techStack());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoftwareEngineer(@PathVariable Integer id) {
        softwareEngineerService.deleteSoftwareEngineer(id);
        return ResponseEntity.noContent().build();
    }

}
