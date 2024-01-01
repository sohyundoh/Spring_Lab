package com.practice.validation.post.service;


import com.practice.validation.post.domain.Post;
import com.practice.validation.post.repository.PostRepository;
import com.practice.validation.user.repository.UserRepository;
import com.practice.validation.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTest {

    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private UserService userService;
    @BeforeEach
    public void init() {
        userService = new UserService(userRepository);
        postService = new PostService(postRepository, userService);
    }
    @Test
    void querydsl_user_id로_찾기() {
        Long userId = 1L;
        Post post = postService.findByUser(userId);
        System.out.println(post.getContent());
    }
}