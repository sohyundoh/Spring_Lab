package org.practice.sse.repository

import org.practice.sse.domain.Member
import org.springframework.data.repository.Repository

interface MemberRepository : Repository<Member, Long> {
    fun save(user : Member) : Member
    fun findById(id : Long) : Member?
}