package com.laboratory.auth.user.service;

import com.laboratory.auth.config.JwtTokenProvider;
import com.laboratory.auth.config.authentication.UserAuthentication;
import com.laboratory.auth.exception.ErrorMessage;
import com.laboratory.auth.exception.model.NotFoundException;
import com.laboratory.auth.exception.model.UnAuthorizedException;
import com.laboratory.auth.external.bot.WebhookService;
import com.laboratory.auth.external.client.kakao.dto.response.KakaoUserResponse;
import com.laboratory.auth.token.service.RefreshTokenService;
import com.laboratory.auth.user.controller.dto.AccessTokenGetSuccess;
import com.laboratory.auth.user.controller.dto.LoginSuccessResponse;
import com.laboratory.auth.user.domain.User;
import com.laboratory.auth.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;
    public Long createUser(final KakaoUserResponse userResponse) {
        User user = User.of(
                userResponse.kakaoAccount().profile().nickname(),
                userResponse.kakaoAccount().profile().profileImageUrl(),
                userResponse.kakaoAccount().profile().accountEmail(),
                userResponse.id()
        );
        return userRepository.save(user).getId();
    }

    public Long getIdBySocialId(
            final Long socialId
    ) {
        User user = userRepository.findBySocialId(socialId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.USER_NOT_FOUND)
        );
        return user.getId();
    }

    public AccessTokenGetSuccess refreshToken(
            final String refreshToken
    ) {
        Long userId = jwtTokenProvider.getUserFromJwt(refreshToken);
        if (!userId.equals(refreshTokenService.findIdByRefreshToken(refreshToken))) {
            throw new UnAuthorizedException(ErrorMessage.TOKEN_INCORRECT_ERROR);
        }
        UserAuthentication userAuthentication = new UserAuthentication(userId, null, null);
        return new AccessTokenGetSuccess(
                jwtTokenProvider.issueAccessToken(userAuthentication)
        );
    }

    public boolean isExistingUser(
            final Long socialId
    ) {
        return userRepository.findBySocialId(socialId).isPresent();
    }

    public LoginSuccessResponse getTokenByUserId(
            final Long id
    ) {
        UserAuthentication userAuthentication = new UserAuthentication(id, null, null);
        String refreshToken = jwtTokenProvider.issueRefreshToken(userAuthentication);
        refreshTokenService.saveRefreshToken(id, refreshToken);
        return LoginSuccessResponse.of(
                jwtTokenProvider.issueAccessToken(userAuthentication),
                refreshToken
        );
    }

    @Transactional
    public void deleteUser(
            final Long id
    ) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(ErrorMessage.USER_NOT_FOUND)
                );
        userRepository.delete(user);
    }
}
