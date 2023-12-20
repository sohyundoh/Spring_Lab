package com.laboratory.auth.user.repository;

import com.laboratory.auth.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
