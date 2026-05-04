package com.business_analytics.model.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;

public class OrderItemsDTO {

  private Integer orderId;
  private Integer productId;
  private Integer quantity;
  private Double price;

  public OrderItemsDTO(Integer orderId, Integer productId, Integer quantity, Double price) {
    this.orderId = orderId;
    this.productId = productId;
    this.quantity = quantity;
    this.price = price;
  }

  // Getters and Setters
  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

}
