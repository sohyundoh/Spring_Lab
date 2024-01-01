package com.practice.validation.post.service;

import com.practice.validation.exception.NotFoundException;
import com.practice.validation.post.domain.Post;
import com.practice.validation.post.repository.PostRepository;
import com.practice.validation.user.domain.User;
import com.practice.validation.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    public Post findByUser(Long userId) {
        User user = userService.findById(userId);
        return postRepository.findByWriter(user).orElseThrow(
                () -> new NotFoundException("user의 게시물이 존재하지 않습니다.")
        );
    }
}
