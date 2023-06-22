package com.sohyun.springsecurityseminar.common.advice;


import com.sohyun.springsecurityseminar.common.dto.ApiResponse;
import com.sohyun.springsecurityseminar.exception.Error;
import com.sohyun.springsecurityseminar.exception.model.NotAuthorityException;
import com.sohyun.springsecurityseminar.exception.model.SecurityException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {
    /*
    403 Forbidden
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(SecurityException.class)
    public ApiResponse handleNotAuthorityException(final SecurityException e){
        return ApiResponse.error(Error.UNAVAILABLE_ACCESS_ERROR, Error.UNAVAILABLE_ACCESS_ERROR.getMessage());
    }
}
