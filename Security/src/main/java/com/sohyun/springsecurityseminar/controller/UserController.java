package com.sohyun.springsecurityseminar.controller;

import com.sohyun.springsecurityseminar.controller.dto.LoginRequestDto;
import com.sohyun.springsecurityseminar.controller.dto.TokenDto;
import com.sohyun.springsecurityseminar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginRequestDto memberLoginRequestDto) {
        String memberId = memberLoginRequestDto.getMemberId();
        String password = memberLoginRequestDto.getPassword();
        TokenDto tokenDto = userService.login(memberId, password);
        return tokenDto;
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}