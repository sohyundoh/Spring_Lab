package org.sopt.laboratory.lab8.product.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.laboratory.lab8.common.dto.ApiResponse;
import org.sopt.laboratory.lab8.common.exception.enums.SuccessType;
import org.sopt.laboratory.lab8.product.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    public ApiResponse getHeartAmount(@PathVariable final Long productId) {
        return ApiResponse.success(SuccessType.PRODUCT_SEARCH_SUCCESS, productService.getHeartAmountFromProduct(productId));
    }
}
