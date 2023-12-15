package org.sopt.laboratory.lab8.product.repository;

import jakarta.persistence.LockModeType;
import org.sopt.laboratory.lab8.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findById(final Long productId);

    @Query("select p.heartAmount from Product p where p.id = :productId")
    int findHeartAmountByProductId(final @Param("productId") Long productId);

}
