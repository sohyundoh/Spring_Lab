package com.lab.websocket.common.dto;

import com.lab.websocket.exception.SuccessMessage;

public record SuccessResponse<T>(
        int status,
        String message,

        T data
) {
    public static <T> SuccessResponse<T> of(
            final SuccessMessage successMessage,
            final T data
    ) {
        return new SuccessResponse<>
                (successMessage.getStatus().value(),
                        successMessage.getMessage(),
                        data);
    }
}
