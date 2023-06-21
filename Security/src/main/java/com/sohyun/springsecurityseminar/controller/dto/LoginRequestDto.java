package com.sohyun.springsecurityseminar.controller.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String memberId;
    private String password;
}
