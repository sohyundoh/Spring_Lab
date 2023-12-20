package com.laboratory.auth.external.client.kakao.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record KakaoAccessTokenResponse(
        String accessToken,
        String refreshToken
) {
    public static KakaoAccessTokenResponse of(
            final String accessToken,
            final String refreshToken
    ) {
        return new KakaoAccessTokenResponse(
                accessToken,
                refreshToken
        );
    }
}
