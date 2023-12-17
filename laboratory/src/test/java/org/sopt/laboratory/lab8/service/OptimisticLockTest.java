package org.sopt.laboratory.lab8.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sopt.laboratory.lab8.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OptimisticLockTest {
    @Autowired
    private ProductService productService;
    final Long PRODUCT_ID = 1L;

    @Test
    @DisplayName("OPTIMISTIC 옵션")
    void getProductByOptimistic() {
        System.out.println("==================Product Optimistic===================");
        productService.getProductById(PRODUCT_ID);
        System.out.println("=======================================================");
    }

    @Test
    @DisplayName("NONE 옵션")
    void getProductByNone() {
        System.out.println("==================Product None===================");
        productService.getProductById_NONE(PRODUCT_ID);
        System.out.println("=======================================================");
    }
}
