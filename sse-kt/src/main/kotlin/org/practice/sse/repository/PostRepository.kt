package org.practice.sse.repository

import org.practice.sse.domain.Post
import org.springframework.data.repository.Repository

interface PostRepository : Repository<Post, Long> {
    fun save(post: Post): Post
}