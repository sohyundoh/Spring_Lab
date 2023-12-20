package com.laboratory.auth.exception.model;

import com.laboratory.auth.exception.ErrorMessage;

public class BadRequestException extends GlobalException{
    public BadRequestException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
