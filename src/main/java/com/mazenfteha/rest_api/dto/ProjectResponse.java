package com.mazenfteha.rest_api.dto;

public record ProjectResponse(
        Integer id,
        String name,
        String description,
        Integer softwareEngineerId
) {

}
