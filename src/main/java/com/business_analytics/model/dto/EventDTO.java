package com.business_analytics.model.dto;

import java.time.LocalDateTime;


public class EventDTO {

  private String type;
  private Long userId;
  private LocalDateTime createdAt;

  public EventDTO(String type, Long userId, LocalDateTime createdAt) {
    this.type = type;
    this.userId = userId;
    this.createdAt = createdAt;
  }

  // Getters and Setters
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
