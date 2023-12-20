package com.laboratory.auth.user.controller;

import com.laboratory.auth.common.dto.SuccessResponse;
import com.laboratory.auth.exception.SuccessMessage;
import com.laboratory.auth.token.service.RefreshTokenService;
import com.laboratory.auth.user.controller.dto.AccessTokenGetSuccess;
import com.laboratory.auth.user.controller.dto.LoginSuccessResponse;
import com.laboratory.auth.user.service.KakaoSocialService;
import com.laboratory.auth.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final KakaoSocialService kakaoSocialService;
    private final UserService userService;
    private final RefreshTokenService tokenService;

    @PostMapping("/kakao/login")
    public SuccessResponse<LoginSuccessResponse> login(
            @RequestParam final String authorizationCode
    ) {
        return SuccessResponse.of(SuccessMessage.SIGNUP_SUCCESS, kakaoSocialService.login(authorizationCode));
    }

    @GetMapping("/token-refresh")
    public SuccessResponse<AccessTokenGetSuccess> refreshToken(
            @RequestParam final String refreshToken
    ) {
        return SuccessResponse.of(SuccessMessage.ISSUE_ACCESS_TOKEN_SUCCESS, userService.refreshToken(refreshToken));
    }

    @DeleteMapping("/delete")
    public SuccessResponse deleteUser(
            final Principal principal
            ) {
        tokenService.deleteRefreshToken(principal.getName());
        userService.deleteUser(Long.valueOf(principal.getName()));
        return SuccessResponse.of(SuccessMessage.USER_DELETE_SUCCESS);
    }

    @PostMapping("/logout")
    public SuccessResponse logout(
            final Principal principal
    ) {
        tokenService.deleteRefreshToken(principal.getName());
        return SuccessResponse.of(SuccessMessage.LOGOUT_SUCCESS);
    }
}
