package org.sopt.laboratory.lab8.heart.controller.dto.response;

import org.sopt.laboratory.lab8.heart.domain.Heart;

public record HeartPutResponse(
        boolean isMade
) {
    public static HeartPutResponse of(Heart heart){
        return new HeartPutResponse(heart != null);  //not null 일때가 true
    }

}