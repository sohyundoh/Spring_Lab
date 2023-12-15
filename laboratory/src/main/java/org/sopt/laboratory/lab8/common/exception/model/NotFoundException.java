package org.sopt.laboratory.lab8.common.exception.model;


import org.sopt.laboratory.lab8.common.exception.enums.ErrorType;

public class NotFoundException extends SoptException {

    public NotFoundException(final ErrorType errorType) {
        super(errorType);
    }
}
