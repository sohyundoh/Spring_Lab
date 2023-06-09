package com.sohyun.springsecurityseminar.exception.model;

import com.sohyun.springsecurityseminar.exception.Error;
import io.jsonwebtoken.security.SignatureException;
import lombok.Getter;

@Getter
public class SecurityException extends SignatureException {
    private final Error error;

    public SecurityException(Error error, String message){
        super(message);
        this.error = error;
    }

    public int getHttpStatus(){
        return error.getHttpStatusCode();
    }
}
