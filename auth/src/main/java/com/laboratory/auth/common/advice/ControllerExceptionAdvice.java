package com.laboratory.auth.common.advice;

import com.laboratory.auth.common.dto.ErrorResponse;
import com.laboratory.auth.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionAdvice {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ErrorResponse handleException(final Exception error, final HttpServletRequest request) throws IOException {
        log.error("================================================NEW===============================================");
        log.error(error.getMessage(), error);
        return ErrorResponse.of(ErrorMessage.INTERNAL_SERVER_ERROR);
    }

}
