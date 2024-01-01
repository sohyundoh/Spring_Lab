package com.practice.validation.controller.dto;

import com.practice.validation.controller.validation.ValidTitle;

public record Request(
        @ValidTitle String title,
        String content
) {
}
