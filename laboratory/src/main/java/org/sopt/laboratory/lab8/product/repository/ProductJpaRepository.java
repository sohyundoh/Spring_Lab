package org.sopt.laboratory.lab8.product.repository;

import jakarta.persistence.LockModeType;
import org.sopt.laboratory.lab8.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    Optional<Product> findById(final Long productId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Product  p where p.id = :productId")
    Optional<Product> findById_PESSIMISTIC(final @Param("productId") Long productId);
    @Lock(LockModeType.NONE)
    @Query("select p from Product  p where p.id = :productId")
    Optional<Product> findById_NONE(final @Param("productId") Long productId);

    @Query("select p.heartAmount from Product p where p.id = :productId")
    int findHeartAmountByProductId(final @Param("productId") Long productId);

}
