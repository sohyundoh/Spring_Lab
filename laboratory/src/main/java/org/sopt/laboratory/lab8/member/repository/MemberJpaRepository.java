package org.sopt.laboratory.lab8.member.repository;

import jakarta.persistence.LockModeType;
import org.sopt.laboratory.lab8.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

}