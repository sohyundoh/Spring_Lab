package com.laboratory.auth.user.controller.dto;

public record SignUpSuccessResponse(
        String accessToken
) {
   public static SignUpSuccessResponse of(
           final String accessToken
   ) {
       return new SignUpSuccessResponse(accessToken);
   }
}