package org.practice.sse.controller

import org.practice.sse.controller.dto.PostCreate
import org.practice.sse.service.PostService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(private val postService: PostService) {
    @PostMapping("/posts")
    fun createPost(@RequestBody postCreate: PostCreate) {
        postService.create(postCreate)
    }
}