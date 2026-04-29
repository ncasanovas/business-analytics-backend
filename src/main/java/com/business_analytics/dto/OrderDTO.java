package com.business_analytics.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;

public class OrderDTO {

  private Integer userId;
  private Double totalAmount;
  private LocalDateTime createdAt;

  public OrderDTO(Integer userId, Double totalAmount, LocalDateTime createdAt) {
    this.userId = userId;
    this.totalAmount = totalAmount;
    this.createdAt = createdAt;
  }

  // Getters and Setters
  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
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
