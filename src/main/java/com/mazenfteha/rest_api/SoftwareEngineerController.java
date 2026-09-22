package com.mazenfteha.rest_api;

import java.util.List;
import java.util.stream.Collectors;

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
import com.mazenfteha.rest_api.dto.SoftwareEngineerResponse;
import com.mazenfteha.rest_api.dto.UpdateSoftwareEngineerRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService softwareEngineerService;

    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    @GetMapping()
    public List<SoftwareEngineerResponse> getAllSoftwareEngineers() {
        return softwareEngineerService.getAllSoftwareEngineers()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @ResponseStatus (HttpStatus.CREATED)
    @PostMapping()
    public SoftwareEngineerResponse createSoftwareEngineer(@Valid @RequestBody CreateSoftwareEngineerRequest request) {
        SoftwareEngineer softwareEngineer = softwareEngineerService.createSoftwareEngineer(null, request.name(),
                request.techStack());
        return toResponse(softwareEngineer);
    }

    @GetMapping("/{id}")
    public SoftwareEngineerResponse getSoftwareEngineerById(@PathVariable Integer id) {
        return toResponse(softwareEngineerService.getSoftwareEngineerById(id));
    }

    @PutMapping("/{id}")
    public SoftwareEngineerResponse updateSoftwareEngineer(@PathVariable Integer id,
            @Valid @RequestBody UpdateSoftwareEngineerRequest request) {
        SoftwareEngineer softwareEngineer = softwareEngineerService.updateSoftwareEngineer(id, request.name(),
                request.techStack());
        return toResponse(softwareEngineer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoftwareEngineer(@PathVariable Integer id) {
        softwareEngineerService.deleteSoftwareEngineer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public Page<SoftwareEngineerResponse> getAllSoftwareEngineers(
            @RequestParam(required = false) String name,
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {

        return softwareEngineerService
                .searchSoftwareEngineers(name, pageable)
                .map(this::toResponse);
    }

    private SoftwareEngineerResponse toResponse(SoftwareEngineer softwareEngineer) {
        return new SoftwareEngineerResponse(
                softwareEngineer.getId(),
                softwareEngineer.getName(),
                softwareEngineer.getTechStack());
    }

}
