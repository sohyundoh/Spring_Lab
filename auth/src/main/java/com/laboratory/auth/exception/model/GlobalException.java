package com.laboratory.auth.exception.model;

import com.laboratory.auth.exception.ErrorMessage;

public class GlobalException extends RuntimeException{
    public GlobalException(ErrorMessage errorMessage){
        super(errorMessage.getMessage());
    }
}
