package com.practice.validation.exception;

public class BadRequestException extends GlobalException{
    public BadRequestException(String message){
        super(message);
    }
}
