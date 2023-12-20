package com.laboratory.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessMessage {
    SIGNUP_SUCCESS(HttpStatus.OK.value(), "회원 가입이 완료되었습니다.")
    ;
    int status;
    String message;
}
