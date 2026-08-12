package com.business_analytics.model.dto;

import com.business_analytics.model.entity.User;


public record TokenResponse(
        String accessToken,
        String refreshToken,
        String tokenType, // "Bearer"
        Long userId,
        String email) {

    public TokenResponse(String accessToken, String refreshToken, User user) {
        this(accessToken, refreshToken, "Bearer", user.getId(), user.getEmail());
    }
}
