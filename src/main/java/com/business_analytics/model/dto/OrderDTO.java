package com.business_analytics.model.dto;

import java.time.LocalDateTime;

public class OrderDTO {

  private Long userId;
  private Double totalAmount;
  private LocalDateTime createdAt;

  public OrderDTO(Long userId, Double totalAmount, LocalDateTime createdAt) {
    this.userId = userId;
    this.totalAmount = totalAmount;
    this.createdAt = createdAt;
  }

  // Getters and Setters
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

}
