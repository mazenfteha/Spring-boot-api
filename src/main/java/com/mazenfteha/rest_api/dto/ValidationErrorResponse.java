package com.mazenfteha.rest_api.dto;

import java.util.Map;

public record ValidationErrorResponse(
        int status,
        String error,
        Map<String, String> fields
) {
}