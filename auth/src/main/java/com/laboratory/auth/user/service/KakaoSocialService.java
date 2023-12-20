package com.laboratory.auth.user.service;

import com.laboratory.auth.exception.ErrorMessage;
import com.laboratory.auth.exception.model.BadRequestException;
import com.laboratory.auth.external.client.kakao.KakaoApiClient;
import com.laboratory.auth.external.client.kakao.KakaoAuthApiClient;
import com.laboratory.auth.external.client.kakao.dto.response.KakaoAccessTokenResponse;
import com.laboratory.auth.external.client.kakao.dto.response.KakaoUserResponse;
import com.laboratory.auth.user.controller.dto.LoginSuccessResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KakaoSocialService extends SocialService {

    private static final String AUTH_CODE = "authorization_code";

    @Value("${kakao.clientId}")
    private String clientId;

    private final UserService userService;
    private final KakaoApiClient kakaoApiClient;
    private final KakaoAuthApiClient kakaoAuthApiClient;

    @Transactional
    @Override
    public LoginSuccessResponse login(final String authorizationCode) {
        String accessToken;
        try {
            // 인가 코드로 Access Token + Refresh Token 받아오기
            accessToken = getOAuth2Authentication(authorizationCode);
        } catch (FeignException e) {
            throw new BadRequestException(ErrorMessage.AUTHENTICATION_CODE_EXPIRED);
        }
        // Access Token으로 유저 정보 불러오기
        KakaoUserResponse userResponse = kakaoApiClient.getUserInformation("Bearer " + accessToken);
        if(userService.isExistingUser(userResponse.kakaoAccount().profile().accountEmail())){
            return userService.getTokenByUserId(userService.getIdByEmail(userResponse.kakaoAccount().profile().accountEmail()));
        }else {
            return userService.getTokenByUserId(userService.createUser(userResponse));
        }
    }

    private String getOAuth2Authentication(
            final String authorizationCode
    ) {
        KakaoAccessTokenResponse tokenResponse = kakaoAuthApiClient.getOAuth2AccessToken(
                AUTH_CODE,
                clientId,
                "http://localhost:8080/kakao/callback",
                authorizationCode
        );
        return tokenResponse.accessToken();
    }


}
