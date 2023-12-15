package org.sopt.laboratory.lab8.product.service;


import lombok.RequiredArgsConstructor;
import org.sopt.laboratory.lab8.common.exception.enums.ErrorType;
import org.sopt.laboratory.lab8.common.exception.model.NotFoundException;
import org.sopt.laboratory.lab8.heart.repository.HeartRepository;
import org.sopt.laboratory.lab8.heart.service.HeartService;
import org.sopt.laboratory.lab8.product.domain.Product;
import org.sopt.laboratory.lab8.product.repository.ProductJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductJpaRepository productJpaRepository;
    private final HeartRepository heartRepository;
    public Product getProductById(final Long productId) {
        return productJpaRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(ErrorType.PRODUCT_NOT_FOUND_EXCEPTION));
    }

    public void decreaseHeart(final Long productId) {
        getProductById(productId).decreaseHeart();
    }

    public void increaseHeart(final Long productId) {
        getProductById(productId).increaseHeart();
    }

    public int getHeartAmountFromProduct(final Long productId) {
        return productJpaRepository.findHeartAmountByProductId(productId);
    }
    public int getHeartAmount(final Long productId) {
        return heartRepository.findByProductId(productId).size();
    }
}
