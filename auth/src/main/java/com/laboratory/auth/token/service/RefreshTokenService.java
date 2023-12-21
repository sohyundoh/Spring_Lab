package com.laboratory.auth.token.service;


import com.laboratory.auth.exception.ErrorMessage;
import com.laboratory.auth.exception.model.NotFoundException;
import com.laboratory.auth.token.domain.Token;
import com.laboratory.auth.token.repository.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {


    private final RedisTemplate<String, Object> redisTemplate;
    private final TokenRepository tokenRepository;

    @Transactional
    public void saveRefreshToken(
            final Long userId,
            final String refreshToken
    ) {
        tokenRepository.save(
                Token.of(
                        userId,
                        refreshToken
                )
        );
    }

    public Long findIdByRefreshToken(
            final String refreshToken
    ) {
        Token token = tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(
                        () -> new NotFoundException(ErrorMessage.REFRESH_TOKEN_NOT_FOUND)
                );
        return token.getId();
    }

    //삭제 로직
    @Transactional
    public void deleteRefreshToken(
            final Long userId
    ) {
        Token token = tokenRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.REFRESH_TOKEN_NOT_FOUND)
        );
        tokenRepository.delete(token);
    }
}