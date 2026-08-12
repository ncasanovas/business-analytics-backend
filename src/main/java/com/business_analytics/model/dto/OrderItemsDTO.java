package com.business_analytics.model.dto;

public class OrderItemsDTO {

  private Long orderId;
  private Long productId;
  private Integer quantity;
  private Double price;

  public OrderItemsDTO(Long orderId, Long productId, Integer quantity, Double price) {
    this.orderId = orderId;
    this.productId = productId;
    this.quantity = quantity;
    this.price = price;
  }

  // Getters and Setters
  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
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
