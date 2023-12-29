package com.lab.websocket.common.dto;

import com.lab.websocket.exception.ErrorMessage;

public record ErrorResponse(
        int status,
        String message
) {

    public static ErrorResponse of(final ErrorMessage errorMessage) {
        return new ErrorResponse(errorMessage.getStatus().value(), errorMessage.getMessage());
    }

    public static ErrorResponse of(final ErrorMessage errorMessage,
                                   final String message) {
        return new ErrorResponse(errorMessage.getStatus().value(), message);
    }
}
