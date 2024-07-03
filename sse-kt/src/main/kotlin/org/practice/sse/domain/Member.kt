package org.practice.sse.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.practice.sse.controller.dto.UserCreate

@Entity
class Member (
    @Column
    val email : String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long?
){
    companion object{
        fun of(userCreate: UserCreate) : Member {
            return Member(userCreate.email, null)
        }
    }
}