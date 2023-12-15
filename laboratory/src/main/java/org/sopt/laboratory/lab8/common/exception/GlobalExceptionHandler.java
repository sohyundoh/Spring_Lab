package org.sopt.laboratory.lab8.common.exception;


import org.sopt.laboratory.lab8.common.dto.ApiResponse;
import org.sopt.laboratory.lab8.common.exception.model.NotFoundException;
import org.sopt.laboratory.lab8.common.exception.model.SoptException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    protected ApiResponse handleNotFoundException(final NotFoundException e) {
        return ApiResponse.error(e.getHttpStatus(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SoptException.class})
    protected ApiResponse handleSoptException(final SoptException e) {
        return ApiResponse.error(e.getHttpStatus(), e.getMessage());
    }
}
