package com.lab.websocket.common.advice;

import com.lab.websocket.common.dto.ErrorResponse;
import com.lab.websocket.exception.ErrorMessage;
import com.lab.websocket.exception.model.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        if (fieldError == null) return ErrorResponse.of(ErrorMessage.VALIDATION_REQUEST_MISSING_EXCEPTION);
        else return ErrorResponse.of(ErrorMessage.VALIDATION_REQUEST_MISSING_EXCEPTION, fieldError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(GlobalException.class)
    public ErrorResponse handleRuntimeException(final GlobalException e) {
        return ErrorResponse.of(ErrorMessage.INTERNAL_SERVER_ERROR);
    }
}

