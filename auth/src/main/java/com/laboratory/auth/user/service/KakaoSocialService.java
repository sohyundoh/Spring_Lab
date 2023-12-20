package com.laboratory.auth.user.service;

import com.laboratory.auth.common.dto.SuccessResponse;
import com.laboratory.auth.config.JwtTokenProvider;
import com.laboratory.auth.config.authentication.UserAuthentication;
import com.laboratory.auth.exception.SuccessMessage;
import com.laboratory.auth.external.client.kakao.KakaoApiClient;
import com.laboratory.auth.external.client.kakao.dto.response.KakaoUserResponse;
import com.laboratory.auth.user.controller.dto.SignUpSuccessResponse;
import com.laboratory.auth.user.domain.User;
import com.laboratory.auth.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KakaoSocialService {

    private final UserRepository userRepository;
    private final KakaoApiClient kakaoApiClient;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public SignUpSuccessResponse signUp(final String accessToken) {
        // Access Token으로 유저 정보 불러오기
        KakaoUserResponse userResponse = kakaoApiClient.getUserInformation("Bearer " + accessToken);

        Long id = createUser(userResponse);

        UserAuthentication userAuthentication = new UserAuthentication(id, null, null);

        return SignUpSuccessResponse.of(jwtTokenProvider.generateToken(userAuthentication));
    }

    private Long createUser(final KakaoUserResponse userResponse) {
        User user = User.of(
                userResponse.kakaoAccount().profile().nickname(),
                userResponse.kakaoAccount().profile().profileImageUrl(),
                userResponse.kakaoAccount().profile().accountEmail()
        );
        return userRepository.save(user).getId();
    }

}
