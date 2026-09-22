package com.mazenfteha.rest_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateSoftwareEngineerRequest(
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    String name,

    @NotBlank(message = "Tech stack is required")
    @Size(max = 255, message = "Tech stack must not exceed 255 characters")
    String techStack
) {

}
