package com.practice.validation.post.repository;

import com.practice.validation.post.domain.Post;
import static com.practice.validation.post.domain.QPost.post;
import com.practice.validation.user.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Post> findByWriter(final User user){
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(post)
                        .where(post.writer.eq(user))
                        .fetchOne()
        );
    }
}