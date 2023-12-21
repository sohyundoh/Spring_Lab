package com.laboratory.auth.user.service;

import com.laboratory.auth.exception.ErrorMessage;
import com.laboratory.auth.exception.model.BadRequestException;
import com.laboratory.auth.external.bot.WebhookService;
import com.laboratory.auth.external.client.kakao.KakaoApiClient;
import com.laboratory.auth.external.client.kakao.KakaoAuthApiClient;
import com.laboratory.auth.external.client.kakao.dto.response.KakaoAccessTokenResponse;
import com.laboratory.auth.external.client.kakao.dto.response.KakaoUserResponse;
import com.laboratory.auth.user.controller.dto.LoginSuccessResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoSocialService extends SocialService {

    private static final String AUTH_CODE = "authorization_code";
    private static final String REDIRECT_URI = "http://localhost:8080/kakao/callback";

    @Qualifier("mailExecutor")
    private final ThreadPoolTaskExecutor mailExecutor;

    @Value("${kakao.clientId}")
    private String clientId;

    private final UserService userService;
    private final KakaoApiClient kakaoApiClient;
    private final KakaoAuthApiClient kakaoAuthApiClient;
    private final WebhookService webhookService;


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
        return getUserInfo(accessToken);
    }

    private String getOAuth2Authentication(
            final String authorizationCode
    ) {
        CompletableFuture<KakaoAccessTokenResponse> future = CompletableFuture.supplyAsync(
                () -> kakaoAuthApiClient.getOAuth2AccessToken(
                        AUTH_CODE,
                        clientId,
                        REDIRECT_URI,
                        authorizationCode
                ), mailExecutor);
        KakaoAccessTokenResponse tokenResponse = future.join();
        return tokenResponse.accessToken();
    }

    private LoginSuccessResponse getUserInfo(
            final String accessToken
    ) {
        KakaoUserResponse userResponse = kakaoApiClient.getUserInformation("Bearer " + accessToken);
        return getTokenDto(userResponse);
    }

    private LoginSuccessResponse getTokenDto(
            final KakaoUserResponse userResponse
    ) {
        if (userService.isExistingUser(userResponse.id())) {
            return userService.getTokenByUserId(userService.getIdBySocialId(userResponse.id()));
        } else {
            Long id = userService.createUser(userResponse);
            webhookService.callEvent(id);
            return userService.getTokenByUserId(id);
        }
    }

}
