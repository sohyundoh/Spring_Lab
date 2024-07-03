package org.practice.sse.controller

import org.practice.sse.service.NotificationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@RestController
class NotificationController(private val notificationService: NotificationService) {
    @GetMapping("/notifications")
    fun getNotifications(@RequestParam memberId : Long): SseEmitter {
        return notificationService.createEmitter(memberId)
    }

}