package com.laboratory.auth.user.repository;

import com.laboratory.auth.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySocialId(final Long socialId);
}
