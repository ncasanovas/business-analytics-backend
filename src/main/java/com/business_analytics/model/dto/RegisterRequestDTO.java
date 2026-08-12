package com.business_analytics.model.dto;

public record RegisterRequestDTO(
    String email,
    String password,
    String name) {

}
