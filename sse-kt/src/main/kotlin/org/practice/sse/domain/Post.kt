package org.practice.sse.domain

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import org.practice.sse.controller.dto.PostCreate

@Entity
class Post(
    var title: String,
    @ManyToOne(fetch = FetchType.LAZY)
    val member: Member,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?
) {
    companion object {
        fun of(postCreate: PostCreate, member: Member): Post {
            return Post(postCreate.title, member, null)
        }
    }
}