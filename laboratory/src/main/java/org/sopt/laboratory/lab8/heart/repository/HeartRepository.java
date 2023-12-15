package org.sopt.laboratory.lab8.heart.repository;


import org.sopt.laboratory.lab8.heart.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    List<Heart> findByProductId(final Long memberId);
    Optional<Heart> findByMemberIdAndProductId(final Long memberId, final Long productId);
}
