package org.practice.sse.domain

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class Notification(
    val title: String,
    val content: String,
    @ManyToOne(fetch = FetchType.LAZY)
    val member: Member,
    val notificationType: NotificationType,
    val notificationTypeId: Long,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?
) {
    companion object {
        fun createPostNotification(member: Member, notificationTypeId: Long): Notification {
            return Notification(
                "새로운 게시글이 등록되었어요.",
                "어쩌구 저쩌구",
                member = member,
                NotificationType.POST,
                notificationTypeId = notificationTypeId,
                id = null
            )
        }
    }
}