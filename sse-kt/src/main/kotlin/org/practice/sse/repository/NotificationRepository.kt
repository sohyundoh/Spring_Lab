package org.practice.sse.repository

import org.practice.sse.domain.Notification
import org.springframework.data.repository.Repository

interface NotificationRepository : Repository<Notification, Long>{
    fun save(notification: Notification) : Notification
}