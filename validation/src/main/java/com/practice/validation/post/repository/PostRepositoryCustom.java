package com.practice.validation.post.repository;

import com.practice.validation.post.domain.Post;
import com.practice.validation.user.domain.User;

import java.util.Optional;

public interface PostRepositoryCustom {
    Optional<Post> findByWriter(final User user);

}
