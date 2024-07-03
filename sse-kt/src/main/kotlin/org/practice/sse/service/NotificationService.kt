package org.practice.sse.service

import org.practice.sse.domain.Notification
import org.practice.sse.repository.NotificationRepository
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Service
class NotificationService(private val notificationRepository: NotificationRepository) {

    var userEmitters: MutableMap<Long, SseEmitter> = mutableMapOf()
    val service: ExecutorService = Executors.newSingleThreadExecutor()

    fun createNotification(notification: Notification) {
        notificationRepository.save(notification)
        //notification 전송
        sendNotification(notification)
    }

    fun createEmitter(memberId: Long): SseEmitter {
        val emitter = SseEmitter(Long.MAX_VALUE)
        userEmitters.put(memberId, emitter)
        emitter.onCompletion { userEmitters.remove(memberId) }
        emitter.onTimeout { userEmitters.remove(memberId) }
        emitter.onError { userEmitters.remove(memberId) }
        return emitter
    }

    fun sendNotification(notification: Notification) {
        val emitter: SseEmitter? = userEmitters.get(notification.member.id)

        emitter?.run {
            service.execute {
                emitter.send(SseEmitter.event().name("이벤트").data(notification))
            }
        }

    }
}