package org.sopt.laboratory.lab8.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sopt.laboratory.lab8.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;
    final Long PRODUCT_ID = 1L;


    @Test
    @DisplayName("좋아요 개수 세기 성능 테스트 - 반정규화 후")
    void countLikeUnRegularize() {
        long startTime = System.currentTimeMillis();
        productService.getHeartAmountFromProduct(PRODUCT_ID);
        long stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);
    }
}
