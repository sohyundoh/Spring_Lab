package com.sohyun.springsecurityseminar.repository;

import com.sohyun.springsecurityseminar.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMemberId(String username);
}