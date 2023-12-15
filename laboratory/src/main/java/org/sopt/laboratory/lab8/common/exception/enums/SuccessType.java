package org.sopt.laboratory.lab8.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessType {

    PRODUCT_SEARCH_SUCCESS(HttpStatus.OK, "상품 상세 조회에 성공하였습니다."),
    HEART_PUT_SUCCESS(HttpStatus.OK,"좋아요 로직이 수행되었습니다."),
    CATEGORY_SEARCH_SUCCESS(HttpStatus.OK, "카테고리 조회에 성공하였습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
