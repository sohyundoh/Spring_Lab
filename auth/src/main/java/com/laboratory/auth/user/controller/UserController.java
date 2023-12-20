package com.laboratory.auth.user.controller;

import com.laboratory.auth.common.dto.SuccessResponse;
import com.laboratory.auth.exception.SuccessMessage;
import com.laboratory.auth.user.controller.dto.SignUpSuccessResponse;
import com.laboratory.auth.user.service.KakaoSocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final KakaoSocialService kakaoSocialService;

    @PostMapping("/kakao/signup")
    public SuccessResponse<SignUpSuccessResponse> signUp(
            @RequestParam final String accessToken
    ) {
        return SuccessResponse.of(SuccessMessage.SIGNUP_SUCCESS, kakaoSocialService.signUp(accessToken));
    }
}
