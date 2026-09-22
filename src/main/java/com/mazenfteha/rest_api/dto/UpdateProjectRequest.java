package com.mazenfteha.rest_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateProjectRequest(
    @NotBlank(message = "Project name is required")
        @Size(max = 100, message = "Project name must not exceed 100 characters")
        String name,
        
        @Size(max = 500, message = "Description must not exceed 500 characters")
        String description,
        
        @NotNull(message = "Software engineer ID is required")
        @Positive(message = "Software engineer ID must be positive")
        Integer softwareEngineerId
) {

}
