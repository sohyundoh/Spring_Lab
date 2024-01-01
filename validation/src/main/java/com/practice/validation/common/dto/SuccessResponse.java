package com.practice.validation.common.dto;

public record SuccessResponse(
        int status,
        String message
) {
    public static SuccessResponse of(final int status, final String message) {
        return new SuccessResponse(status, message);
    }
}
