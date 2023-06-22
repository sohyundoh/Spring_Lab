package com.sohyun.springsecurityseminar.common.advice;


import com.sohyun.springsecurityseminar.common.dto.ApiResponse;
import com.sohyun.springsecurityseminar.exception.Error;
import com.sohyun.springsecurityseminar.exception.model.NotAuthorityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

@RestControllerAdvice
public class ControllerExceptionAdvice {
    /*
    403 Forbidden
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(NotAuthorityException.class)
    public ApiResponse handleNotAuthorityException(final NotAuthorityException e){
        return ApiResponse.error(Error.UNAVAILABLE_ACCESS_ERROR, Error.UNAVAILABLE_ACCESS_ERROR.getMessage());
    }
}
