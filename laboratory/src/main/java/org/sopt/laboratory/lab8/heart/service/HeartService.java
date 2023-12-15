package org.sopt.laboratory.lab8.heart.service;

import lombok.RequiredArgsConstructor;
import org.sopt.laboratory.lab8.common.exception.enums.ErrorType;
import org.sopt.laboratory.lab8.common.exception.model.NotFoundException;
import org.sopt.laboratory.lab8.heart.controller.dto.response.HeartPutResponse;
import org.sopt.laboratory.lab8.heart.domain.Heart;
import org.sopt.laboratory.lab8.heart.repository.HeartRepository;
import org.sopt.laboratory.lab8.member.domain.Member;
import org.sopt.laboratory.lab8.member.service.MemberService;
import org.sopt.laboratory.lab8.product.domain.Product;
import org.sopt.laboratory.lab8.product.service.ProductService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HeartService {
    private final HeartRepository heartRepository;
    private final MemberService memberService;
    private final ProductService productService;

    public HeartPutResponse toggleHeart(Long memberId, Long productId) {
        try {
            Heart newHeart = create(memberId, productId);
            productService.increaseHeart(productId);
            return HeartPutResponse.of(newHeart);
        } catch (DataIntegrityViolationException e) {
            productService.decreaseHeart(productId);
            delete(memberId, productId);
            return HeartPutResponse.of(null);
        }
    }

    public HeartPutResponse createHeart(final Long memberId, final Long productId) {
        productService.increaseHeart(productId);
        return HeartPutResponse.of(create(memberId, productId));
    }

    public HeartPutResponse deleteHeart(final Long memberId, final Long productId) {
        delete(memberId, productId);
        productService.decreaseHeart(productId);
        return HeartPutResponse.of(null);
    }

    public void delete(Long memberId, Long productId) {
        Heart heart = heartRepository.findByMemberIdAndProductId(memberId, productId).orElseThrow(
                () -> new NotFoundException(ErrorType.HEART_NOT_FOUND_EXCEPTION)
        );
        heartRepository.delete(heart);
    }

    private Heart create(Long memberId, Long productId) {
        Member member = memberService.getMemberById(memberId);
        Product product = productService.getProductById(productId);
        Heart newHeart = Heart.create(member, product);
        heartRepository.save(newHeart);
        return newHeart;
    }

}