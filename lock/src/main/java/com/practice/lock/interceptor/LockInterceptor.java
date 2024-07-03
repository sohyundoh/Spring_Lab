package com.practice.lock.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class LockInterceptor implements HandlerInterceptor {

    private final RedissonClient redissonClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
        final String lockKey = (cachingRequest.getHeader("Host") + objectMapper.readTree(cachingRequest.getContentAsByteArray()));
        RLock lock = redissonClient.getLock(lockKey);
        try {
            boolean isLock = lock.tryLock(0, 0, TimeUnit.SECONDS);
            if (!isLock) {
                response.setStatus(500);
                throw new RuntimeException();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
        final String lockKey = (cachingRequest.getHeader("Host") + objectMapper.readTree(cachingRequest.getContentAsByteArray()));
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }
}