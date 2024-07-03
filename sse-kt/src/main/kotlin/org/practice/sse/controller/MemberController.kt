package org.practice.sse.controller

import org.practice.sse.controller.dto.UserCreate
import org.practice.sse.service.MemberService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(private val memberService: MemberService) {
    @RequestMapping("/users")
    fun create(@RequestBody userCreate: UserCreate) {
        memberService.createUser(userCreate)
    }

}