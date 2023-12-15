package org.sopt.laboratory.lab8.common.exception.model;

import org.sopt.laboratory.lab8.common.exception.enums.ErrorType;

public class SoptException extends RuntimeException{
    private final ErrorType error;

    public SoptException(final ErrorType error){
        super(error.getMessage());
        this.error = error;
    }
    public int getHttpStatus(){
        return error.getHttpStatus().value();
    }
}
