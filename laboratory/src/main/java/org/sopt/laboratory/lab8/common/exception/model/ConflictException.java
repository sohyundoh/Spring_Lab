package org.sopt.laboratory.lab8.common.exception.model;

import org.sopt.laboratory.lab8.common.exception.enums.ErrorType;

public class ConflictException extends SoptException {

    public ConflictException(final ErrorType errorType) {
        super(errorType);
    }
}