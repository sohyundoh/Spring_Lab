package org.practice.sse.service

import org.practice.sse.controller.dto.UserCreate
import org.practice.sse.domain.Member
import org.practice.sse.exception.NotFoundException
import org.practice.sse.repository.MemberRepository
import org.springframework.stereotype.Service


@Service
class MemberService (private val memberRepository: MemberRepository) {

    fun createUser(userCreate: UserCreate) {
        val user = Member.of(userCreate)
        memberRepository.save(user)
    }

    fun findById(id: Long): Member {
        return memberRepository.findById(id) ?: throw NotFoundException("유저가 없습니다.")
    }
}