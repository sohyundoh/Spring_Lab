package com.lab.websocket.exception.model;

import com.lab.websocket.exception.SuccessMessage;

public class GlobalException extends RuntimeException{

    public GlobalException(SuccessMessage successMessage) {
        super(successMessage.getMessage());
    }
}
