package com.practice.validation.user.service;


import com.practice.validation.exception.NotFoundException;
import com.practice.validation.user.domain.User;
import com.practice.validation.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("유저가 존재하지 않습니다."));
    }
}