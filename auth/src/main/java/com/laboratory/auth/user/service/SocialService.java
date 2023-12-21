package com.laboratory.auth.user.service;

import com.laboratory.auth.user.controller.dto.LoginSuccessResponse;

public abstract class SocialService {
    public abstract LoginSuccessResponse login(final String authorizationCode);
}
