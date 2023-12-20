package com.laboratory.auth.exception.model;

import com.laboratory.auth.exception.ErrorMessage;

public class NotFoundException extends GlobalException{
    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
