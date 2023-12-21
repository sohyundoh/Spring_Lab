package com.laboratory.auth.exception.model;

import com.laboratory.auth.exception.ErrorMessage;

public class UnAuthorizedException extends GlobalException{
    public UnAuthorizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
