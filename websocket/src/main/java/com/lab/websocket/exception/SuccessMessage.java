package com.lab.websocket.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessMessage {
    BOARD_CREATE_SUCCESS(HttpStatus.OK, "게시글이 정상적으로 생성되었습니다."),
    ;
    private HttpStatus status;
    private String message;
}
