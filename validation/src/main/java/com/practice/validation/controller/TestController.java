package com.practice.validation.controller;

import com.practice.validation.common.dto.SuccessResponse;
import com.practice.validation.controller.dto.Request;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping
    public SuccessResponse test( @RequestBody @Valid final Request request) {
        return SuccessResponse.of(200, "500에 걸리게 해봅시댜");
    }
}
