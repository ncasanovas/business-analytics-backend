package com.business_analytics.model.entity;

import java.time.LocalDateTime;

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
@Table(name = "order")
public class Order {

  @Id @GeneratedValue
  private Integer id;

  @Column(name="user_id") 
  private Integer userId;

   @Column(name="total_amount") 
  private Double totalAmount;

  @Column(name="created_at") 
  private LocalDateTime createdAt;
  
}
