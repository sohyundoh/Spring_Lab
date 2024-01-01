package com.practice.validation.common.dto;

public record ErrorResponse(
        int status,
        String message
) {
    public static ErrorResponse of(final int status, final String message) {
        return new ErrorResponse(status, message);
    }
}
