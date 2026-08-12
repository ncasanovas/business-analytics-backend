package com.business_analytics.model.dto;

public record ErrorResponse(
    int status,
    String message
  ) {}
