package com.business_analytics.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "order_items")
public class OrderItems {

  
  @Id @GeneratedValue
  private Integer id;

  @Column(name="order_id")
  private Integer orderId;

  @Column(name="product_id")
  private Integer productId;

  private Integer quantity;
  private Double price;

  
}
