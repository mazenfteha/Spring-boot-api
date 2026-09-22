package com.mazenfteha.rest_api.dto;

public record ApiErrorResponse(
    int status,
    String error,
    String path
) {

}
