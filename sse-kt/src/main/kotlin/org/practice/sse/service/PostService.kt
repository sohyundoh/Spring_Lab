package org.practice.sse.service

import org.practice.sse.controller.dto.PostCreate
import org.practice.sse.domain.Post
import org.practice.sse.domain.Member
import org.practice.sse.domain.Notification
import org.practice.sse.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(
    private val userService: MemberService,
    private val notificationService: NotificationService,
    private val postRepository: PostRepository
) {
    fun create(postCreate: PostCreate) {
        val user : Member = userService.findById(1L)
        val post : Post = Post.of(postCreate, user)
        postRepository.save(post)
        //notification 저장
        val notification : Notification = Notification.createPostNotification(user, post.id!!)
        //notification 생성
        notificationService.createNotification(notification)
    }
}