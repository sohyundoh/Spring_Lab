package com.sohyun.springsecurityseminar.exception.model;

import com.sohyun.springsecurityseminar.exception.Error;

public class NotAuthorityException extends SecurityException{
    public NotAuthorityException(Error error, String message){
        super(error, message);
    }
}
