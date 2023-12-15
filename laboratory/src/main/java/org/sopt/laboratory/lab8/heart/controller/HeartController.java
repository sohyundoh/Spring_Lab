package org.sopt.laboratory.lab8.heart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.laboratory.lab8.common.dto.ApiResponse;
import org.sopt.laboratory.lab8.common.exception.enums.SuccessType;
import org.sopt.laboratory.lab8.heart.controller.dto.response.HeartPutResponse;
import org.sopt.laboratory.lab8.heart.service.HeartService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/heart")
@RequiredArgsConstructor
@Slf4j
public class HeartController {

    private final HeartService heartService;


    @PutMapping("/{memberId}/{productId}")
    public ApiResponse<HeartPutResponse> toggleHeart(@PathVariable Long memberId, @PathVariable Long productId) {
        return ApiResponse.success(SuccessType.HEART_PUT_SUCCESS, heartService.toggleHeart(memberId, productId));
    }

    @PostMapping("/{memberId}/{productId}")
    public ApiResponse<HeartPutResponse> createHeart(@PathVariable final Long memberId, @PathVariable final Long productId) {
        log.info("=============create===============");
        return ApiResponse.success(SuccessType.HEART_PUT_SUCCESS, heartService.createHeart(memberId, productId));
    }

    @DeleteMapping("/{memberId}/{productId}")
    public ApiResponse<HeartPutResponse> deleteHeart(@PathVariable final Long memberId, @PathVariable final Long productId) {
        log.info("================delete================");
        return ApiResponse.success(SuccessType.HEART_PUT_SUCCESS, heartService.deleteHeart(memberId, productId));
    }
}

